package main;

import java.time.LocalDate;
import java.util.List;

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

public class TestListSubjectExecuteAction extends Action{

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
			int cN = 0;
			int su = 0;
			String subject;
			String numStr;
			List<Test> tests = null;//学生リスト
			LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			TestDao tDao = new TestDao();//学生Dao
			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			boolean hasError = false;


			//リクエストパラメーターの取得
			entYearStr = request.getParameter("f1");
			classNum = request.getParameter("f2");
			subject = request.getParameter("f3");


			// 入学年度をintに変換
			int num = 0;
			if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
			    entYear = Integer.parseInt(entYearStr);
			}

			if (classNum != null && !classNum.isEmpty() && !classNum.equals("0")) {
			    cN = Integer.parseInt(classNum);
			}

			if (subject != null && !subject.isEmpty() && !subject.equals("0")) {
			    su = Integer.parseInt(subject);
			}

			// エラーメッセージ
			if (subject != null && !subject.equals("0")) {
				System.out.println("subject");
			}



			List<String> list = cNumDao.filter(teacher.getSchool());

			SubjectDao subjectDao = new SubjectDao();
			List<Subject> subjectList = subjectDao.filter(teacher.getSchool());



			if (entYear == 0 || cN == 0 || su == 0) {
				String errors = "入学年度とクラスと科目を選択してください。";
				request.setAttribute("errors", errors);
				hasError = true;
			}

	        // エラーがあった場合は再入力画面へ戻す
	        if (hasError) {
	        	request.setAttribute("entYear", entYear);
	            request.setAttribute("classNum", classNum);
	            request.setAttribute("subject", subject);

	            // `StudentCreateAction` から渡されたクラス情報を再度セット
	            List<String> classNumList = cNumDao.filter(teacher.getSchool());
	            request.setAttribute("class_num_set", classNumList);
	            request.setAttribute("subjects", subjectList);



	            request.getRequestDispatcher("/main/test_list.jsp").forward(request, response);
	            return;
	        }


			Subject sub = new Subject();
			sub.setCd(subject);

			if (entYear != 0 && !classNum.equals("0")) {
				//testsに検索した内容を入れる
				tests = tDao.filter(entYear, classNum, sub, num, teacher.getSchool());
				if (tests != null) {
				    System.out.println("Tests list size: " + tests.size());
				}
			}

				session.setAttribute("tests", tests);

				request.setAttribute("subjects", subjectList);

				//レスポンス値をセット
				//リクエストに入学年度をセット
				request.setAttribute("f1", entYear);
				//リクエストにクラス番号をセット
				request.setAttribute("f2", classNum);
				//リクエストに科目をセット
				request.setAttribute("f3", subject);

				//リクエストにテストリストをセット
				request.setAttribute("tests", tests);
				request.setAttribute("class_num_set", list);

				//JSPへフォワード
				request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);

	}
}
