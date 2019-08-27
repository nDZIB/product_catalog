package com.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.category.Category;
import com.category.CategoryManagementService;

@WebServlet(urlPatterns = "/add-new-product.pcat")
@MultipartConfig(maxFileSize = 16177215)
public class AddNewProduct extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable)
			throws ServletException, IOException {

		String productName = requestVariable.getParameter("productName");
		String productDescription = requestVariable.getParameter("productDescription");
		String productColor = requestVariable.getParameter("productColor");
		String categoryName = requestVariable.getParameter("categoryName");
		String categoryDescription = requestVariable.getParameter("categoryDescription");
		int productPrice = Integer.parseInt(requestVariable.getParameter("productPrice"));
		// String pict = requestVariable.getParameter("productView");


		Product product = new Product(categoryName, categoryDescription, productName, productDescription, productColor, productPrice);
		// set the current product as session variable, this is to enable it to be
		// accessible even after
		// this request

		requestVariable.getSession().setAttribute("product", product);
		// redirect to the page to modify products
		requestVariable.getRequestDispatcher("/WEB-INF/views/add-new-product.jsp").forward(requestVariable,
				responseVariable);
	}

	
}
