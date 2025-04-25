package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {


	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception{
		HttpSession session = request.getSession();
		//Teacher teacher = (Teacher)session.getAttribute("user");

		Teacher teacher = new Teacher();
		teacher.setId("admin1");
			teacher.setPassword("password");
			teacher.setName("管理者1");
			// 仮に学校情報を設定（Schoolオブジェクトがある場合）
			School school = new School();
			school.setName("テスト校");
			school.setCd("tes");
			teacher.setSchool(school);


			// セッションにTeacherオブジェクトを保存
			session.setAttribute("user", teacher);

		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化


		//ビジネスロジック

		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());


		//リクエストにクラス番号をセット
		request.setAttribute("class_num_set", list);

		//JSPへフォワード
		request.getRequestDispatcher("student_create.jsp").forward(request, response);

	}
}


