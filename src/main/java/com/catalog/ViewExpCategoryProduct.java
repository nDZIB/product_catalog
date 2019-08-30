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
import com.product.ProductManagementService;

@WebServlet(urlPatterns = "/view-expcategory-product.pcat")
public class ViewExpCategoryProduct extends HttpServlet{

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
		
		//CategoryManagementService categoryMService = new CategoryManagementService();
		int catID = new CategoryManagementService().getCategoryID(category);
		
		List<Product> products = new ProductManagementService().getAllCategoryProducts(catID);
		System.out.println(products.size());
		
		requestVariable.setAttribute("category", category);
		requestVariable.setAttribute("products", products);
		requestVariable.getRequestDispatcher("/WEB-INF/views/view-expcategory-product.jsp").forward(requestVariable, responseVariable);
	}
}
