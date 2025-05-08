package main;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import bean.Teacher;
=======
import bean.ClassNum;
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
import dao.ClassNumDao;
@WebServlet( urlPatterns ={"/main/student_create"} )

public class StudentCreateAction extends HttpServlet {


	public void doPost
		( HttpServletRequest request, HttpServletResponse response
<<<<<<< HEAD
		) throws Exception{
		HttpSession session = request.getSession();
		//一時的にコメントアウトしているteacherインスタンス
		Teacher teacher = (Teacher)session.getAttribute("teacher");
=======
		) throws java.io.IOException {

	try {
		 // クラス情報一覧取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<ClassNum> studentClassList = classNumDao.filter();
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git


<<<<<<< HEAD
		//ClassNumDaoのインスタンス作成
		ClassNumDao cNumDao = new ClassNumDao();
=======
        // クラスリストをリクエストにセット
        request.setAttribute("studentClassList", studentClassList);
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git

<<<<<<< HEAD

		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());


		//リクエストにクラス番号をセット
		request.setAttribute("class_num_set", list);

		//JSPへフォワード
		request.getRequestDispatcher("student_create.jsp").forward(request, response);

=======
        // 入力画面へフォワード
        request.getRequestDispatcher("/main/student_create.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(500);  // エラー発生時は500を返す
    }
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
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


}

