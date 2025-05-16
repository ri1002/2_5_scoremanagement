package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.StudentTestDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // ログインチェック
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 学生番号取得
        String studentId = request.getParameter("f4");
        School school = teacher.getSchool();

        if (studentId == null || studentId.isEmpty()) {
            request.getRequestDispatcher("/main/test_list.jsp").forward(request, response);
            return;
        }

        // 成績情報を取得
        StudentTestDao dao = new StudentTestDao();
        List<TestListStudent> tests = dao.filter(studentId, school);

        StudentDao studentDao = new StudentDao();
        Student selectedStudent = studentDao.get(studentId);  // studentIdがnoに対応
        request.setAttribute("selectedStudent", selectedStudent);

        // JSPに渡す
        request.setAttribute("tests", tests);
        request.setAttribute("f4", studentId);

        // 表示先のJSPへフォワード
        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}
