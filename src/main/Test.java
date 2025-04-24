package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import dao.ClassNumDao;;

//クラス
@WebServlet(urlPatterns={"/main/test"})
public class Test extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		//DBからクラスを全件取得する。
		ClassNumDao cDao = new ClassNumDao();
		try{
			List<ClassNum> cList = cDao.filter();
			request.setAttribute("cList", cList);
		}catch(Exception e){
			e.printStackTrace(response.getWriter());
		}

		request.getRequestDispatcher("/main/test_regist.jsp").forward(request, response);

	}
}