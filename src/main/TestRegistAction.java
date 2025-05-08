package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");

		if (teacher == null) {
		    response.sendRedirect("login.jsp"); // ログインページに飛ばすなどの処理
		    return;
		}
			// セッションにTeacherオブジェクトを保存
			session.setAttribute("user", teacher);

			String entYearStr="";//入力された入学年度
			String classNum=""; //入力されたクラス番号
			int entYear = 0;//入学年度
			String subject;
			String numStr;
			List<Test> tests = null;//学生リスト
			LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			TestDao tDao = new TestDao();//学生Dao
			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			Map<String, String> errors = new HashMap<>();//エラーメッセージ


			//リクエストパラメーターの取得
			entYearStr = request.getParameter("f1");
			classNum = request.getParameter("f2");
			subject = request.getParameter("f3");
			numStr = request.getParameter("f4");

			// 入学年度をintに変換
			int num = 0;
			if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
			    try {
			        entYear = Integer.parseInt(entYearStr);
			    } catch (NumberFormatException e) {
			        errors.put("errors", "入学年度の形式が正しくありません");
			    }
			}

			// テスト回数をintに変換
			if (numStr != null && !numStr.isEmpty() && !numStr.equals("0")) {
			    try {
			        num = Integer.parseInt(numStr);
			    } catch (NumberFormatException e) {
			        errors.put("errors", "テスト回数の形式が正しくありません");
			    }
			}

			// エラーメッセージ
			/*if (subject != null && !subject.equals("0")) {
				System.out.println("subject");
			}*/


			List<String> list = cNumDao.filter(teacher.getSchool());

			Subject sub = new Subject();
			sub.setCd(subject);

			if (entYear != 0 && !classNum.equals("0")) {
				//testsに検索した内容を入れる
				tests = tDao.filter(entYear, classNum, sub, num, teacher.getSchool());
				if (tests != null) {
				    System.out.println("Tests list size: " + tests.size());
				}
			} else {
				// エラーメッセージ
				if (subject != null && !subject.equals("0")) {
					System.out.println("subject");
				}
				else {
			        errors.put("errors", "入学年度とクラスと科目と回数を選択してください");
				}
			}

				SubjectDao subjectDao = new SubjectDao();
				List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
				request.setAttribute("subjects", subjectList);



				//レスポンス値をセット
				//リクエストに入学年度をセット
				request.setAttribute("f1", entYear);
				//リクエストにクラス番号をセット
				request.setAttribute("f2", classNum);
				//リクエストに科目をセット
				request.setAttribute("f3", subject);
				//リクエストにテスト回数をセット
				request.setAttribute("f4", num);

				//リクエストにテストリストをセット
				request.setAttribute("tests", tests);
				request.setAttribute("class_num_set", list);

				request.setAttribute("errors", errors);
				//JSPへフォワード
				request.getRequestDispatcher("test_regist.jsp").forward(request, response);

	}
}
