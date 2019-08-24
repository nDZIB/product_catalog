package systemuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns ="/logout.pcat")
public class UserLoggOut extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		//remove the user from session and redirect them to the view-catalog
		if(requestVariable.getSession().getAttribute("userName") != null)
			requestVariable.getSession().removeAttribute("userName");
		//redirect
		responseVariable.sendRedirect("/view-catalog.pcat");
	}

}
