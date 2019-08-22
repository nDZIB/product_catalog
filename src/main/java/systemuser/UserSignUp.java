package systemuser;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionManager;

@WebServlet(urlPatterns = "/user-signup.pcat")
public class UserSignUp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		requestVariable.getRequestDispatcher("/WEB-INF/views/user-signup.jsp").forward(requestVariable,
				responseVariable);
	}

	@Override
	protected void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		Connection dbconnection = (Connection) requestVariable.getSession().getAttribute("dbconnection");
		if (dbconnection == null) {
			dbconnection = new ConnectionManager().createConnection();
			requestVariable.getSession().setAttribute("dbconnection", dbconnection);
		}
		String userName = requestVariable.getParameter("userName");
		String userPassword = requestVariable.getParameter("userPassword");
		String userRealName = requestVariable.getParameter("userRealName");

		System.out.println(userName);
		System.out.println(userPassword);
		System.out.println(userRealName);

		// after having the necessary information, verify that user does not exist
		// if user does not exist, then allow signup, other wise prompt user to change
		// their name
		UserValidation userValidator = new UserValidation();
		SystemUser newsystemuser = new SystemUser(userRealName, userName, userPassword);

		boolean userISSignedUp = userValidator.signupUser(dbconnection, newsystemuser);

		// if the user is succesfully signed up, log them in// or give them forum to log
		// in
		if (userISSignedUp) {// if the user is successfully signed up
			System.out.println("signed up");

			// UserValidation userValidator = new UserValidation();
			boolean userExists = userValidator.userExists(dbconnection, userName, userPassword);
			if (userExists) {
				requestVariable.getSession().setAttribute("userName", userName);
				responseVariable.sendRedirect("/view-exp-catalog.pcat");
			} else {// if the user does not exist, keep user in view-catalog
				responseVariable.sendRedirect("/view-catalog.pcat");
			}

			System.out.println("Newly signed up user has been signed in");
		} else {// send them back to sign up, if they were not successfully signed up

			responseVariable.sendRedirect("/user-signup.pcat");
			// requestVariable.getRequestDispatcher("/WEB-INF/views/user-signup.jsp").forward(requestVariable,
			// responseVariable);
		}
	}

}
