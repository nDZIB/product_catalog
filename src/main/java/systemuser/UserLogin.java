package systemuser;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionManager;

@WebServlet(urlPatterns = "/login.pcat")
public class UserLogin extends HttpServlet{
	
	
	//get the username and password,
	//authenticate the user
	//grant access to explicit view otherwise keep them in basic view
	@Override
	protected void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		Connection dbconnection =  (Connection)requestVariable.getSession().getAttribute("dbconnection");
		if(dbconnection == null) {
			dbconnection = new ConnectionManager().createConnection();
			requestVariable.getSession().setAttribute("dbconnection", dbconnection);
		}
		String userName = requestVariable.getParameter("userName");
		String userPassword = requestVariable.getParameter("userPassword");
		
		UserValidation userValidator = new UserValidation();
		boolean userExists = userValidator.userExists(dbconnection, userName, userPassword);
		if(userExists) {
			requestVariable.getSession().setAttribute("userName", userName);
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		} else {//if the user does not exist, keep user in view-catalog
			responseVariable.sendRedirect("/view-catalog.pcat");
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		Connection dbconnection = null; //(Connection)requestVariable.getSession().getAttribute("dbconnection");
		if(dbconnection == null) {
			dbconnection = new ConnectionManager().createConnection();
			requestVariable.getSession().setAttribute("dbconnection", dbconnection);
		}
		String userName = (String)requestVariable.getAttribute("userName");
		String userPassword = (String)requestVariable.getAttribute("userPassword");
		
		System.out.println(userName);
		System.out.println(userPassword);
		UserValidation userValidator = new UserValidation();
		boolean userExists = userValidator.userExists(dbconnection, userName, userPassword);
		if(userExists) {
			requestVariable.getSession().setAttribute("userName", userName);
			responseVariable.sendRedirect("/view-exp-catalog.pcat");
		} else {//if the user does not exist, keep user in view-catalog
			responseVariable.sendRedirect("/view-catalog.pcat");
		}
		
	}
	
}
