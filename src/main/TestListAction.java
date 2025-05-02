package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{

	@Override
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

			String entYearStr="";//入力された入学年度
			String classNum=""; //入力されたクラス番号
			int entYear = 0;//入学年度
			boolean isAttend = false;//在学フラグ
			List<Student> students = null;//学生リスト
			LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			StudentDao sDao = new StudentDao();//学生Dao
			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			Map<String, String> errors = new HashMap<>();//エラーメッセージ


			//リクエストパラメーターの取得
			entYearStr = request.getParameter("f1");
			classNum = request.getParameter("f2");


			List<String> list = cNumDao.filter(teacher.getSchool());

			if (entYear != 0 && !classNum.equals("0")) {
				// 入学年度とクラス番号を指定
				students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
			} else if (entYear != 0 && classNum.equals("0")) {
					// 入学年度のみ
					students = sDao.filter(teacher.getSchool(), entYear, isAttend);
			} else {
				errors.put("errors", "検索条件が正しくありません");
			}

			SubjectDao subjectDao = new SubjectDao();
			List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
			request.setAttribute("subjects", subjectList);

		//レスポンス値をセット
		//リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);

		//リクエストに学生リストをセット
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", list);

		//JSPへフォワード
		request.getRequestDispatcher("test_list.jsp").forward(request, response);

	}
}
