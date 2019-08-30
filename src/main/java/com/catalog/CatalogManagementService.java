package com.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.ConnectionManager;
import com.product.Product;

public class CatalogManagementService {
	List<Product> products = new ArrayList<Product>();// list to hold all available products

	// get all the products
	public List<Product> getProducts() {
		Connection databaseConnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID");

			// after getting the list of products, put them in request scope and forward to
			// view

			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				int productPrice = setOfProducts.getInt(2);
				byte[] productView = setOfProducts.getBytes(3);
				String productDescription = setOfProducts.getString(4);
				String productColor = setOfProducts.getString(5);
				String categoryName = setOfProducts.getString(6);
				String categoryDescription = setOfProducts.getString(7);
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor, productView, productPrice);

				// add to list
				products.add(product);
			}
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products fails print error to console
		}
		return products;
	}
	
	
	public List<Product> getAllCategoryProducts(int catID) {;
		List<Product> products = new ArrayList<Product>();
		Connection databaseConnection = new ConnectionManager().createConnection();
		try {
			System.out.println("Category ID "+catID);
			PreparedStatement dbManipulate = databaseConnection
					.prepareStatement("SELECT " + "productName, productPrice, productView, productDescription, productColor, categoryName, "
							+ "categoryDescription FROM product INNER JOIN category WHERE "
							+ "product.categoryID = category.categoryID AND product.categoryID = ?");

			// after getting the list of products, put them in request scope and forward to
			// view
			dbManipulate.setInt(1, catID);

			ResultSet setOfProducts = dbManipulate.executeQuery();

			// obtain list of products and add to a list
			while (setOfProducts.next()) {
				String productName = setOfProducts.getString(1);
				int productPrice = setOfProducts.getInt(2);
				byte[] productView = setOfProducts.getBytes(3);
				String productDescription = setOfProducts.getString(4);
				String productColor = setOfProducts.getString(5);
				String categoryName = setOfProducts.getString(6);
				String categoryDescription = setOfProducts.getString(7);
				
				// instantiate a product object
				Product product = new Product(categoryName, categoryDescription, productName, productDescription,
						productColor, productView, productPrice);

				// add to list
				products.add(product);
				System.out.println(products.size());
			}
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();// if getting the products sfails print error to console
		}
		return products;
	}
}
