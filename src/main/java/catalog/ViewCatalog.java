package catalog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import connection.ConnectionManager;
import product.Product;




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
		
		if(databaseConnection!= null) {
			//if the connection was successful
			//put the connection into session
			requestVariable.getSession().setAttribute("dbconnection", databaseConnection);
			//execute a query to get the list of all products
			try {
				PreparedStatement dbManipulate = databaseConnection.prepareStatement("SELECT "
						+ "productName, productDescription, productColor, categoryName, "
						+ "categoryDescription FROM product INNER JOIN category WHERE "
						+ "product.categoryID = category.categoryID");
				
				//after getting the list of products, put them in request scope and forward to view
				
				ResultSet setOfProducts = dbManipulate.executeQuery();
				
				//obtain list of products and add to a list
				while(setOfProducts.next()) {
					String productName = setOfProducts.getString(1);
					String productDescription = setOfProducts.getString(2);
					String productColor = setOfProducts.getString(3);
					String categoryName = setOfProducts.getString(4);
					String categoryDescription = setOfProducts.getString(5);
					//instantiate a product object
					Product product = new Product(categoryName, categoryDescription, productName, productDescription, productColor);
					
					//add to list
					products.add(product);
				}
				
				//put the list in request scope
				requestVariable.setAttribute("products", products);
				
				//forward the request to the view(view-catalog)
				requestVariable.getRequestDispatcher("/WEB-INF/views/view-catalog.jsp").forward(requestVariable, responseVariable);	
			} catch (SQLException e) {
				e.printStackTrace();//if getting the products fails print error to console
			}
		} else {//else if the connection to the database was unsuccessful
			System.out.println("Unable to connect to database");
		}
		
	}
}
