package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {


	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception{
		HttpSession session = request.getSession();
		//一時的にコメントアウトしているteacherインスタンス
		Teacher teacher = (Teacher)session.getAttribute("teacher");


		//ClassNumDaoのインスタンス作成
		ClassNumDao cNumDao = new ClassNumDao();


		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());


		//リクエストにクラス番号をセット
		request.setAttribute("class_num_set", list);

		//JSPへフォワード
		request.getRequestDispatcher("student_create.jsp").forward(request, response);

	}
}


