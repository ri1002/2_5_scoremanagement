package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {


	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception{
		HttpSession session = request.getSession();

		boolean hasError = false;

		Teacher teacher = (Teacher)session.getAttribute("teacher");

			// セッションにTeacherオブジェクトを保存
			session.setAttribute("user", teacher);



		// リクエストパラメータから学生番号を取得
	    String SubjectCd = request.getParameter("cd");
	    String SubjectName = request.getParameter("name");


		if (SubjectName == null || SubjectName.trim().isEmpty()) {
			String student_name = "このフィールドを入力してください。";
			hasError = true;
		}

        // エラーがあった場合は再入力画面へ戻す
        if (hasError) {

    	    // そのIDに対応する学生情報をDBから取得する
        	SubjectDao subjectdao = new SubjectDao();
    	    Subject subjectGet = subjectdao.get(SubjectCd);

    		//リクエストに変更したい学生情報をセット
    		request.setAttribute("subjectGet", subjectGet);

            request.getRequestDispatcher("/main/subject_update.jsp").forward(request, response);
            return;
        }


	    // そのIDに対応する科目情報をDBから取得する
	    Subject subject = new Subject();
	    subject.setSchool(teacher.getSchool());
	    subject.setCd(SubjectCd);
	    subject.setName(SubjectName);

	    SubjectDao subjectdao = new SubjectDao();

	    boolean subjectSave = subjectdao.save(subject);



		//リクエストに変更したい学生情報をセット
		request.setAttribute("subject", subject);


		//JSPへフォワード
		request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);

	}
}


