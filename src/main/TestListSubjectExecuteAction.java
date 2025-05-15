package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.SubjectTestDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

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
        String classNumStr = request.getParameter("f2");
        String subject = request.getParameter("f3");

        int entYear = 0;
        int classNum = 0;
        boolean hasError = false;

        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (classNumStr != null && !classNumStr.isEmpty() && !classNumStr.equals("0")) {
            classNum = Integer.parseInt(classNumStr);
        }
        if (subject == null || subject.isEmpty() || subject.equals("0")) {
            hasError = true;
        }

        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

        if (hasError || entYear == 0 || classNum == 0) {
            String errors = "入学年度とクラスと科目を選択してください。";
            request.setAttribute("errors", errors);

            request.setAttribute("entYear", entYearStr);
            request.setAttribute("classNum", classNumStr);
            request.setAttribute("subject", subject);
            request.setAttribute("class_num_set", classNumList);
            request.setAttribute("subjects", subjectList);

            request.getRequestDispatcher("/main/test_list.jsp").forward(request, response);
            return;
        }

        SubjectTestDao testDao = new SubjectTestDao();
        List<Test> tests = testDao.filter(entYear, classNum, subject, teacher.getSchool());

        // ★ここで選択された科目情報を取得してJSPに渡す
        Subject selectedSubject = null;
        for (Subject s : subjectList) {
            if (s.getCd().equals(subject)) {
                selectedSubject = s;
                break;
            }
        }

        //session.setAttribute("tests", tests);
        request.setAttribute("tests", tests);
        request.setAttribute("subjects", subjectList);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNumStr);
        request.setAttribute("f3", subject);

        // ★ここでJSPに渡す
        request.setAttribute("selectedSubject", selectedSubject);

        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}