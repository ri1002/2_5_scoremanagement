package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        System.out.println("ログイン試行 - 入力ID: " + id);
        System.out.println("ログイン試行 - 入力Password: " + password);
        System.out.println("Teacher オブジェクト: " + (teacher != null ? "存在する" : "null"));

        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            request.getRequestDispatcher("Menu.action").forward(request, response);
        } else {
            session.removeAttribute("teacher"); // セッションから不正なデータを削除
            session.setAttribute("error", "IDまたはパスワードが違います");
            response.sendRedirect("../login.jsp"); 
        }
    }
}
