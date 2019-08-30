package com.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.category.Category;
import com.category.CategoryManagementService;
import com.product.Product;

@WebServlet(urlPatterns = "/view-category-product.pcat")
public class ViewCategoryProduct extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		//Connection databaseConnecton = new ConnectionManager().createConnection();
		
		Category category = new Category(categoryName, categoryDescription);
		
		int catID = new CategoryManagementService().getCategoryID(category);
		
		List<Product> products = new CatalogManagementService().getAllCategoryProducts(catID);
		requestVariable.setAttribute("category", category);
		requestVariable.setAttribute("products", products);
		requestVariable.getRequestDispatcher("/WEB-INF/views/view-category-product.jsp").forward(requestVariable, responseVariable);
	}
}
