package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		//エラーが出るのでとりあえずセットしてます
		String set = "とりあえずなんかセット";

		request.setAttribute("set", set);
		return "/login.jsp";
	}
}
