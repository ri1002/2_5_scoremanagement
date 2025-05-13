package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

	@Override
	protected void doGet (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
			String path = request.getServletPath().substring(1);
			String name = path.replace(".action", "Action").replace("/", ".");

			System.out.println("★ servlet path -> " + request.getServletPath());
			System.out.println("★ class name -> " + name);

			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

			//action.execute(request, response);

			String nextPage = action.execute(request, response);
			request.getRequestDispatcher("/" + nextPage).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}


	protected void doPost(
		HttpServletRequest request,HttpServletResponse response
	) throws ServletException, IOException {
		doGet(request,response);
	}
}