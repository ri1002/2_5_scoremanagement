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

public class TestRegistExecuteAction extends Action {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        request.getSession().setAttribute("user", teacher);

        String[] regists = request.getParameterValues("regist");
        String[] points = request.getParameterValues("point");
        String[] counts = request.getParameterValues("count");
        String[] subjects = request.getParameterValues("subject");

        HttpSession session = request.getSession();
        List<Test> tests = (List<Test>) session.getAttribute("tests");

        List<Test> list = new ArrayList<>();

        for (int i = 0; i < regists.length; i++) {

        	if (regists[i] == null || regists[i].trim().isEmpty() ||
        	        counts.length <= i || subjects.length <= i) {
        	        continue; // 不正な入力をスキップ
        	    }

            String pointStr = points[i];
            Integer point = null;

            if (pointStr != null && !pointStr.trim().isEmpty()) {
                try {
                    point = Integer.parseInt(pointStr);
                } catch (NumberFormatException e) {
                    point = null;
                }
                if (point < 0 || point > 100) {
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

            //各項目がnullでないことを確認
            String regist = regists[i];
            int count = Integer.parseInt(counts[i]);
            String subjectCd = subjects[i];

            Student student = new Student();
            student.setNo(regist);

            Subject subject = new Subject();
            subject.setCd(subjectCd);

            System.out.println("teacher = " + teacher);
            System.out.println("regists[i] = " + regists[i]);
            System.out.println("counts[i] = " + counts[i]);
            System.out.println("subjects[i] = " + subjects[i]);
            System.out.println("point = " + point);


            Test test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setNo(count);
            test.setPoint(point);  // null可
            test.setSchool(teacher.getSchool());

            list.add(test);
        }

        TestDao dao = new TestDao();
        dao.save(list);

        session.removeAttribute("tests");
        request.getRequestDispatcher("/main/test_regist_done.jsp").forward(request, response);
    }
}
