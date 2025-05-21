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


        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            response.sendRedirect("Menu.action");
            //request.getRequestDispatcher("Menu.action").forward(request, response);
        } else {
        	request.setAttribute("id", id);
        	request.setAttribute("password", password);
            session.removeAttribute("teacher"); // セッションから不正なデータを削除
            session.setAttribute("error", "IDまたはパスワードが確認できませんでした");
            response.sendRedirect("../login.jsp");
        }
    }
}
