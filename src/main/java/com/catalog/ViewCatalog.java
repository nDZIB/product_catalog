package com.catalog;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionManager;
import com.product.Product;




@WebServlet(urlPatterns = "/view-catalog.pcat")
public class ViewCatalog extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requestVariable, HttpServletResponse responseVariable) 
		throws ServletException, IOException {
		/*when the user visits the landing page, access is created to the database and the
		the product catalog is displayed*/
		
		
		List<Product> products = new ArrayList<Product>();//list to hold all available products
		ConnectionManager connectionManager = new ConnectionManager();
		//get connection to the product catalog database
		Connection databaseConnection = connectionManager.createConnection();
		CatalogManagementService catalogMService = new CatalogManagementService();
		
		if(databaseConnection!= null) {
			//if the connection was successful
			//put the connection into session
			requestVariable.getSession().setAttribute("dbconnection", databaseConnection);
			
			products = catalogMService.getProducts(databaseConnection);
			if(products.size() != 0) {
				//put the list in request scope
				requestVariable.setAttribute("products", products);
				
				//forward the request to the view(view-catalog)
				requestVariable.getRequestDispatcher("/WEB-INF/views/view-catalog.jsp").forward(requestVariable, responseVariable);	
			} else {//if there are no products to display
				System.out.println("No products to display");
			}
		} else {//else if the connection to the database was unsuccessful
			System.out.println("Unable to connect to database");
		}
		
	}
}
