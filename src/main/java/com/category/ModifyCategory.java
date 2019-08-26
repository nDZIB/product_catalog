package com.category;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/modify-category.pcat")
public class ModifyCategory extends HttpServlet {
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
		requestVariable.getRequestDispatcher("/WEB-INF/views/modify-category.jsp").forward(requestVariable,
				responseVariable);
	}

	@Override
	public void doPost(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {
		/* Do post handles deletion and modification of a category/categories */

		CategoryManagementService manageCategory = new CategoryManagementService();

		Connection dbconnection = (Connection) requestVariable.getSession().getAttribute("dbconnection");
		if (requestVariable.getParameter("deleteCategory") != null) {// if user wishes to delete a category
			Category category = (Category) requestVariable.getSession().getAttribute("category");

			manageCategory.removeCategory(dbconnection, category);
		} else if (requestVariable.getParameter("editCategory") != null) {// if a user wishes to edit a category
			String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
			String newCategoryName = requestVariable.getParameter("newCategoryName").toString();

			Category newCategory = new Category(newCategoryName, newCategoryDescription);
			Category oldcategory = (Category) requestVariable.getSession().getAttribute("category");

			boolean categoryIsValid = newCategory.categoryIsComplete();
			if (categoryIsValid) {
				if (manageCategory.editCategory(dbconnection, oldcategory, newCategory))
					System.out.println("Category updated");
			} else {
				System.out.println("ModifyCategory: Category was not added, it seems all information was not provided");
			}
			
		} else if (requestVariable.getParameter("addNewCategory") != null) {
			// execute query to add a category, then return to the view-exp-catalog jsp
			// first get category, then add it
			String newCategoryDescription = requestVariable.getParameter("newCategoryDescription").toString();
			String newCategoryName = requestVariable.getParameter("newCategoryName").toString();

			Category newCategory = new Category(newCategoryName, newCategoryDescription);
			
			boolean categoryIsComplete = newCategory.categoryIsComplete();
			if(categoryIsComplete) {
				if(manageCategory.addNewCategory(dbconnection, newCategory))
					System.out.println("Modify Category: Category was modified");
			} else {
				System.out.println("Modify Category: Category was modified");
			}
		}
		requestVariable.getSession().removeAttribute("category");//remove the old category from session after performing relevant operation
		responseVariable.sendRedirect("/view-exp-categories.pcat");//and return the user to be able to explicitly view products
	}
}
