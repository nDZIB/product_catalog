package systemuser;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/user-signup.pcat")
public class UserSignUp extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
		throws ServletException, IOException {
		requestVariable.getRequestDispatcher("/WEB-INF/views/user-signup.jsp").forward(requestVariable, responseVariable);
	}
	
	@Override
	protected void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		Connection dbconnection =  (Connection)requestVariable.getSession().getAttribute("dbconnection");
		String userName = requestVariable.getParameter("userName");
		String userPassword = requestVariable.getParameter("userPassword");
		String userRealName = requestVariable.getParameter("userRealName");
		
		
		//after having the necessary information, verify that user does not exist
		//if user does not exist, then allow signup, other wise prompt user to change their name
		UserValidation userValidator = new UserValidation();
		SystemUser newsystemuser = new SystemUser(userRealName, userName, userPassword);
		
		boolean userISSignedUp = userValidator.signupUser(dbconnection, newsystemuser);
		
		//if the user is succesfully signed up redirect them to explicitly view 
		if(userISSignedUp) {
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		} else {//send them back to sign up
			requestVariable.getRequestDispatcher("/WEB-INF/views/user-signup.jsp").forward(requestVariable, responseVariable);
		}
	}
	
}
