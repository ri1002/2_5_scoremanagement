package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;;

//クラス
@WebServlet(urlPatterns={"/main/test"})
public class Test extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{


		Teacher teacher = new Teacher();
		teacher.setId("admin1");
			teacher.setPassword("password");
			teacher.setName("管理者1");
			// 仮に学校情報を設定（Schoolオブジェクトがある場合）
			School school = new School();
			school.setName("テスト校");
			school.setCd("tes");
			teacher.setSchool(school);


		//DBからクラスを全件取得する。
		ClassNumDao cDao = new ClassNumDao();
		try{
			List<String> cList = cDao.filter(teacher.getSchool());
			request.setAttribute("cList", cList);
		}catch(Exception e){
			e.printStackTrace(response.getWriter());
		}

		request.getRequestDispatcher("/main/test_regist.jsp").forward(request, response);

	}
}