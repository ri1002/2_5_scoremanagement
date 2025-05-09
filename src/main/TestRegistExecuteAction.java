
package main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            // 教師情報の取得
            Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
            request.getSession().setAttribute("user", teacher);

            // 各種データを配列で取得
            String[] regists = request.getParameterValues("regist");
            String[] points = request.getParameterValues("point");
            String[] counts = request.getParameterValues("count");
            String[] subjects = request.getParameterValues("subject");

            //リスト
            List<Test> list = new ArrayList<>();


            for (int i = 0; i < regists.length; i++) {

            	int point;

                point = Integer.parseInt(points[i]);

            if (point < 0 || point > 100) {
                request.setAttribute("error", "点数は0～100の範囲で入力してください");

                SubjectDao subjectDao = new SubjectDao();
    			List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

    			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Dao
                List<String> classNumList = cNumDao.filter(teacher.getSchool());

	            request.setAttribute("class_num_set", classNumList);
	            request.setAttribute("subjects", subjectList);
                request.getRequestDispatcher("/main/test_regist.jsp").forward(request, response);
                return;
            }

                String regist = regists[i];
                int point = Integer.parseInt(points[i]);
                int count = Integer.parseInt(counts[i]);
                String subjectCd = subjects[i];

                Student student = new Student();
                student.setNo(regist);

                Subject subject = new Subject();
                subject.setCd(subjectCd);

                Test test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setNo(count);
                test.setPoint(point);
                test.setSchool(teacher.getSchool());

                list.add(test);
            }

            TestDao dao = new TestDao();
            dao.save(list);

            request.getRequestDispatcher("/main/test_regist_done.jsp").forward(request, response);
        }
    }