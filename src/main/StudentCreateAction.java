package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
import bean.Teacher;
<<<<<<< HEAD
=======

import bean.ClassNum;

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {


	public void execute
		( HttpServletRequest request, HttpServletResponse response
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		) throws Exception{
		HttpSession session = request.getSession();
		//一時的にコメントアウトしているteacherインスタンス
		Teacher teacher = (Teacher)session.getAttribute("teacher");
<<<<<<< HEAD
=======

		) throws java.io.IOException {

	try {
		 // クラス情報一覧取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<ClassNum> studentClassList = classNumDao.filter();
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		//ClassNumDaoのインスタンス作成
		ClassNumDao cNumDao = new ClassNumDao();
<<<<<<< HEAD
=======

        // クラスリストをリクエストにセット
        request.setAttribute("studentClassList", studentClassList);
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git


		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());


		//リクエストにクラス番号をセット
		request.setAttribute("class_num_set", list);

		//JSPへフォワード
		request.getRequestDispatcher("student_create.jsp").forward(request, response);

<<<<<<< HEAD
=======

        // 入力画面へフォワード
        request.getRequestDispatcher("/main/student_create.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(500);  // エラー発生時は500を返す
    }

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
	}
}


