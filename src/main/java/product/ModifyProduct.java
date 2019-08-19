package product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/modify-product.pcat")
public class ModifyProduct  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
		throws ServletException, IOException {
		requestVariable.getRequestDispatcher("/WEB-INF/views/modify-product.jsp").forward(requestVariable,responseVariable);
	}
}
