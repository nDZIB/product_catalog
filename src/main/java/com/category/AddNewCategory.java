package com.category;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/add-new-category.pcat")
public class AddNewCategory extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		// first, get the parameters
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");

		Category category = new Category(categoryName, categoryDescription);

		requestVariable.getSession().setAttribute("category", category);// put the current category to be modified in
																		// session
		requestVariable.getRequestDispatcher("/WEB-INF/views/add-new-category.jsp").forward(requestVariable,
				responseVariable);
	}
}
