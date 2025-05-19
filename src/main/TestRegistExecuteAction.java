package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TestRegistExecuteAction extends Action {

    // doPost を明示的に使っている場合（必要なら残す）
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        session.setAttribute("user", teacher);

        String[] regists = request.getParameterValues("regist");
        String[] points = request.getParameterValues("point");
        String[] counts = request.getParameterValues("count");
        String[] subjects = request.getParameterValues("subject");

        @SuppressWarnings("unchecked")
        List<Test> tests = (List<Test>) session.getAttribute("tests");

        // studentNo → classNum マップを作成（testsリストから）
        Map<String, String> studentClassNumMap = new HashMap<>();
        if (tests != null) {
            for (Test t : tests) {
                Student st = t.getStudent();
                if (st != null && st.getNo() != null && t.getClassNum() != null) {
                    studentClassNumMap.put(st.getNo(), t.getClassNum());
                }
            }
        }

        List<Test> list = new ArrayList<>();

        for (int i = 0; i < regists.length; i++) {
            if (regists[i] == null || regists[i].trim().isEmpty() ||
                counts.length <= i || subjects.length <= i) {
                continue;
            }

            String pointStr = (points != null && points.length > i) ? points[i] : null;
            Integer point = null;

            if (pointStr != null && !pointStr.trim().isEmpty()) {
                try {
                    point = Integer.parseInt(pointStr.trim());
                    if (point < 0 || point > 100) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "点数は0～100の範囲で入力してください");

                    SubjectDao subjectDao = new SubjectDao();
                    List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

                    ClassNumDao cNumDao = new ClassNumDao();
                    List<String> classNumList = cNumDao.filter(teacher.getSchool());

                    request.setAttribute("class_num_set", classNumList);
                    request.setAttribute("subjects", subjectList);
                    request.setAttribute("tests", tests);

                    request.getRequestDispatcher("/main/test_regist.jsp").forward(request, response);
                    return;
                }
            }

            String studentNo = regists[i];
            int testNo = Integer.parseInt(counts[i]);
            String subjectCd = subjects[i];

            Student student = new Student();
            student.setNo(studentNo);
            student.setClassNum(studentClassNumMap.get(studentNo));  // ← ここでclassNumセット

            Subject subject = new Subject();
            subject.setCd(subjectCd);

            Test test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setNo(testNo);
            test.setPoint(point);
            test.setSchool(teacher.getSchool());
            test.setClassNum(student.getClassNum()); // ← こちらもセット

            list.add(test);
        }

        TestDao dao = new TestDao();
        dao.save(list);

        session.removeAttribute("tests");

        request.getRequestDispatcher("/main/test_regist_done.jsp").forward(request, response);
    }
}
