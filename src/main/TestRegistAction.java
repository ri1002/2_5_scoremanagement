package main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        session.setAttribute("user", teacher);

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subject = request.getParameter("f3");
        String numStr = request.getParameter("f4");

        int entYear = 0;
        int cN = 0;
        int su = 0;
        int num = 0;
        boolean hasError = false;

        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (classNum != null && !classNum.isEmpty() && !classNum.equals("0")) {
            cN = Integer.parseInt(classNum);
        }
        if (subject != null && !subject.isEmpty() && !subject.equals("0")) {
            su = Integer.parseInt(subject);
        }
        if (numStr != null && !numStr.isEmpty() && !numStr.equals("0")) {
            num = Integer.parseInt(numStr);
        }

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        if (entYear == 0 || cN == 0 || su == 0 || num == 0) {
            String errors = "入学年度とクラスと科目と回数を選択してください。";
            request.setAttribute("errors", errors);
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("entYear", entYear);
            request.setAttribute("classNum", classNum);
            request.setAttribute("subject", subject);
            request.setAttribute("num", num);
            request.setAttribute("class_num_set", classNumList);
            request.setAttribute("subjects", subjectList);
            request.getRequestDispatcher("/main/test_regist.jsp").forward(request, response);
            return;
        }

        Subject selectedSubject = null;
        for (Subject s : subjectList) {
            if (s.getCd().equals(subject)) {
                selectedSubject = s;
                break;
            }
        }

        TestDao tDao = new TestDao();
        List<Test> registeredTests = tDao.filter(entYear, classNum, selectedSubject, num, teacher.getSchool());
        List<Student> unregisteredStudents = tDao.findStudentsWithoutTest(entYear, classNum, selectedSubject, num, teacher.getSchool());

        List<Test> tests = new ArrayList<>(registeredTests);
        for (Student student : unregisteredStudents) {
            Test test = new Test();
            test.setStudent(student);
            test.setSubject(selectedSubject);
            test.setNo(num);
            test.setPoint(null); // 未入力
            test.setSchool(teacher.getSchool());
            test.setClassNum(student.getClassNum());
            tests.add(test);
        }

        session.setAttribute("tests", tests);
        request.setAttribute("tests", tests);
        request.setAttribute("selectedSubject", selectedSubject);
        request.setAttribute("subjects", subjectList);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subject);
        request.setAttribute("f4", num);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}
