package com.catalog;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.category.Category;
import com.category.CategoryManagementService;
import com.connection.ConnectionManager;
import com.product.Product;
import com.product.ProductManagementService;

@WebServlet(urlPatterns = "/view-category-product.pcat")
public class ViewCategoryProduct extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		Connection databaseConnecton = new ConnectionManager().createConnection();
		
		Category category = new Category(categoryName, categoryDescription);
		
		int catID = new CategoryManagementService().getCategoryID(databaseConnecton, category);
		
		List<Product> products = new ProductManagementService().getAllCategoryProducts(databaseConnecton, catID);
		requestVariable.setAttribute("category", category);
		requestVariable.setAttribute("products", products);
		requestVariable.getRequestDispatcher("/WEB-INF/views/view-category-product.jsp").forward(requestVariable, responseVariable);
	}
}
