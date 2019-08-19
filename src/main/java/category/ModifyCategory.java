package category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/modify-category.pcat")
public class ModifyCategory  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
		throws ServletException, IOException {
		//first, get the parameters
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		requestVariable.getRequestDispatcher("/WEB-INF/views/modify-category.jsp");
	}
}
