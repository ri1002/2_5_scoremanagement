package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {


	public void execute

		( HttpServletRequest request, HttpServletResponse response

		) throws Exception{

		HttpSession session = request.getSession();

		//一時的にコメントアウトしているteacherインスタンス

		Teacher teacher = (Teacher)session.getAttribute("teacher");

		//JSPへフォワード

		request.getRequestDispatcher("subject_create.jsp").forward(request, response);

	}

}


