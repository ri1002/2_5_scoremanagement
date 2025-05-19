package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {


	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception{
		HttpSession session = request.getSession();

		Teacher teacher = (Teacher)session.getAttribute("teacher");

			// セッションにTeacherオブジェクトを保存
			session.setAttribute("user", teacher);


		// リクエストパラメータから学生番号を取得
	    String SubjectCd = request.getParameter("code");


	    // そのIDに対応する科目情報をDBから取得する
	    SubjectDao subjectdao = new SubjectDao();
	    Subject subject = subjectdao.get(SubjectCd);



		//リクエストに変更したい学生情報をセット
		request.setAttribute("subject", subject);


		//JSPへフォワード
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);

	}
}


