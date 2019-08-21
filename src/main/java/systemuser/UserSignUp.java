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
public class UserSignUp extends HttpServlet{
	
	
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
		
		
		System.out.println(userName);
		System.out.println(userPassword);
		System.out.println(userRealName);
		UserValidation userValidator = new UserValidation();
		boolean userExists = userValidator.userExists(dbconnection, userName, userPassword);
		if(userExists) {
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		} else {//if the user does not exist, keep user in view-catalog
			responseVariable.sendRedirect("/view-catalog.pcat");
		}
		
	}
	
}
