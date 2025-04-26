package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {


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

		// リクエストパラメータから学生番号を取得
	    String studentNo = request.getParameter("no");

	    // そのIDに対応する学生情報をDBから取得する
	    StudentDao studentdao = new StudentDao();
	    Student student = studentdao.get(studentNo);


	    ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoのインスタンスを作成

		//ビジネスロジック

		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		//リクエストに変更したい学生情報をセット
		request.setAttribute("student", student);

		//リクエストにクラス番号をセット
		request.setAttribute("class_num_set", list);


		//JSPへフォワード
		request.getRequestDispatcher("student_update.jsp").forward(request, response);

	}
}


