package com.catalog;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.category.CategoryManagementService;
import com.connection.ConnectionManager;

@WebServlet(urlPatterns = "/view-exp-categories.pcat")
public class ViewExpCategories extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException{
		
		Connection dbconnection = new ConnectionManager().createConnection();
		CategoryManagementService categoryMService = new CategoryManagementService();
		
		//get all the categories and set them as a request attribute
		requestVariable.setAttribute("category", categoryMService.getAllCategories(dbconnection));
		//forward the request to the view
		requestVariable.getRequestDispatcher("/WEB-INF/views/view-exp-categories.jsp").forward(requestVariable, responseVariable);
	}
	
	
}