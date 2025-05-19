package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
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

		boolean hasError = false;

    SubjectDao subjectdao = new SubjectDao();
    Subject existingSubject = subjectdao.get(cd);



	//nameがnullの時エラー
	if (name == null || name.trim().isEmpty()) {
		String error_subject_name = "このフィールドを入力してください。";
		request.setAttribute("error_subject_name", error_subject_name);
		hasError = true;
	}

	//cdがnullの時エラー
	if (cd == null || cd.trim().isEmpty()) {
		String error_subject_cd = "このフィールドを入力してください。";
		request.setAttribute("error_subject_cd", error_subject_cd);
		hasError = true;
	}
	//cdが三文字以下の時エラー
	else if (cd.length() != 3) {
		String error_subject_cd_number= "科目コードは3文字で入力してください";
		request.setAttribute("error_subject_cd_number", error_subject_cd_number);
		hasError = true;
	}


    // エラーがあった場合は再入力画面へ戻す
    if (hasError) {

		//リクエストに変更したい学生情報をセット
		request.setAttribute("subject", existingSubject);


        request.getRequestDispatcher("/main/subject_create.jsp").forward(request, response);
        return;
    }

    Subject exiSubject = subjectdao.get(cd);

	if ( exiSubject != null) {
		String subject_duplication = "学生番号が重複しています";

		request.setAttribute("subject_duplication", subject_duplication);

		// 入力値を戻すためのセット（必要であれば）
		request.setAttribute("cd", cd);
		request.setAttribute("name", name);


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