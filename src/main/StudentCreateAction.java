/*package main;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import dao.ClassNumDao;
@WebServlet( urlPatterns ={"/main/student_create"} )

public class StudentCreateAction extends HttpServlet {


	public void doPost
		( HttpServletRequest request, HttpServletResponse response
		) throws java.io.IOException {

	try {
		 // クラス情報一覧取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<ClassNum> studentClassList = classNumDao.filter();


        // クラスリストをリクエストにセット
        request.setAttribute("studentClassList", studentClassList);

        // 入力画面へフォワード
        request.getRequestDispatcher("/main/student_create.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(500);  // エラー発生時は500を返す
    }
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws java.io.IOException {

        try {
            ClassNumDao classNumDao = new ClassNumDao();
            List<ClassNum> studentClassList = classNumDao.filter();

            request.setAttribute("studentClassList", studentClassList);
            request.getRequestDispatcher("/main/student_create.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
	}


}*/

