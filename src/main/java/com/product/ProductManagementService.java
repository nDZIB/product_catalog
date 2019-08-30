package com.product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.ConnectionManager;

public class ProductManagementService {

	// retrieve the product id
	public int getProductID(Product product) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("SELECT productID FROM product WHERE productName = ? AND "
							+ "productDescription = ? AND productColor = ? AND productPrice = ?");// you might want to edit this query to
																				// include other fields
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDescription());
			pst.setString(3, product.getProductColor());
			pst.setInt(4, product.getProductPrice());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				return (rs.getInt(1));
			}
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// retrieve category id
	public int getCategoryID(Product product) {
		Connection dbconnection = new ConnectionManager().createConnection();
		
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("SELECT categoryID FROM product WHERE productName = ? AND "
							+ "productDescription = ? AND productColor = ? AND productPrice = ?");// you might want to edit this query to
																				// include other fields
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDescription());
			pst.setString(3, product.getProductColor());
			pst.setInt(4, product.getProductPrice());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				return (rs.getInt(1));
			}

			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("updated product");
		return 0;
	}

	// modify/edit a product
	public boolean editProduct(Product newproduct, int newcategoryID, int oldproductID) {
		Connection dbconnection = new ConnectionManager().createConnection();
		
		try {
				PreparedStatement pst2 = dbconnection
						.prepareStatement("UPDATE product SET categoryID = ?, productName = ?, productPrice = ?, "
								+ "productDescription = ?, productColor = ? WHERE productID = ?");
				pst2.setInt(1, newcategoryID);
				pst2.setString(2, newproduct.getProductName());
				pst2.setInt(3, newproduct.getProductPrice());
				pst2.setString(4, newproduct.getProductDescription());
				pst2.setString(5, newproduct.getProductColor());
				pst2.setInt(6, oldproductID);

				pst2.executeUpdate();
				dbconnection.close();
		} catch (SQLException ex2) {
			ex2.printStackTrace();
			return false;
		}
		return true;
	}

	
	//edit an existing product given that an image is supplied
	public boolean editProduct(Product newproduct, int newcategoryID, int oldproductID, InputStream productView) {
		Connection dbconnection = new ConnectionManager().createConnection();
		
		try {
			PreparedStatement pst2 = dbconnection.prepareStatement("UPDATE product SET categoryID = ?, productName = ?, "
					+ "productPrice = ?, productDescription = ?, productView = ?, productColor = ? WHERE productID = ?");
			
			
			pst2.setInt(1, newcategoryID);
			pst2.setString(2, newproduct.getProductName());
			pst2.setInt(3, newproduct.getProductPrice());
			pst2.setString(4, newproduct.getProductDescription());
			pst2.setBlob(5, productView);
			pst2.setString(6, newproduct.getProductColor());
			pst2.setInt(7, oldproductID);

			pst2.executeUpdate();
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// remove a product
	public boolean removeProduct(Product product) {

		Connection dbconnection = new ConnectionManager().createConnection();

		try {// for better code, you might want to first get the relevant category id before
				// proceeding
			PreparedStatement pst = dbconnection.prepareStatement("DELETE FROM product WHERE productName=? AND "
					+ "productDescription = ? AND productPrice = ?");
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDescription());
			pst.setInt(3, product.getProductPrice());
			pst.executeUpdate();

			dbconnection.close();
			System.out.println("Okay, product deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nothing deleted");
			return false;
		}
		return true;
	}

	// add a new product without image provided
	public boolean addProduct(Product product, int categoryID) {
		Connection dbconnection = new ConnectionManager().createConnection();
		
		//PreparedStatement pst;
		try {
			PreparedStatement pst = dbconnection.prepareStatement("INSERT IGNORE INTO product(categoryID, productName, productPrice, "
					+ "productDescription, productColor) VALUES(?,?,?,?,?)");

			pst.setInt(1, categoryID);
			pst.setString(2, product.getProductName());
			pst.setInt(3, product.getProductPrice());
			pst.setString(4, product.getProductDescription());
			pst.setString(5, product.getProductColor());

			// execute the query
			pst.executeUpdate();
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// add a product to products with an image of the product supplied
	public boolean addProduct(Product product, int categoryID, InputStream productView) {
		Connection dbconnection = new ConnectionManager().createConnection();
		PreparedStatement pst;
		try {
			pst = dbconnection.prepareStatement(
					"INSERT IGNORE INTO product(categoryID, productName, productPrice, productView, productDescription,"
							+ " productColor) VALUES(?,?,?,?,?,?)");

			pst.setInt(1, categoryID);
			pst.setString(2, product.getProductName());
			pst.setInt(3, product.getProductPrice());
			pst.setBlob(4, productView);
			pst.setString(5, product.getProductDescription());
			pst.setString(6, product.getProductColor());

			// execute the query
			pst.executeUpdate();
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
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
			e.printStackTrace();// if getting the products fails print error to console
		}
		return products;
	}
}
