package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;


public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute
	( HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");

		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

    StudentDao studentdao = new StudentDao();
    Student existingStudent = studentdao.get(cd);


	if ( existingStudent != null) {
		// クラス情報の再取得とセット

		// フォワード
		request.getRequestDispatcher("/main/subject_create.jsp").forward(request, response);
		return;
	}

		Subject subject = new Subject();
		subject.setCd(cd);              // 学生番号
		subject.setName(name);       	// 名前

		School school = new School();      // 学校情報（省略してたらここ必要！）
		school.setCd(teacher.getSchool().getCd());

		subject.setSchool(school);         // 学校コードをセット

		SubjectDao dao = new SubjectDao();
		boolean result = dao.save(subject);  // ← 登録・更新の実行
		request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);

	}

}