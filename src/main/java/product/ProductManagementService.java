package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManagementService {

	// retrieve the product id
	public int getProductID(Connection dbconnection, Product product) {
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("SELECT productID FROM product WHERE productName = ? AND "
							+ "productDescription = ? AND productColor = ?");// you might want to edit this query to
																				// include other fields
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDescription());
			pst.setString(3, product.getProductColor());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				return (rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//retrieve category id
	public int getCategoryID(Connection dbconnection, Product product) {
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("SELECT categoryID FROM product WHERE productName = ? AND "
							+ "productDescription = ? AND productColor = ?");// you might want to edit this query to
																				// include other fields
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDescription());
			pst.setString(3, product.getProductColor());

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				return (rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//modify/edit a product
	public boolean editProduct(Connection dbconnection, Product newproduct, int newcategoryID, int oldproductID) {
		try {
			PreparedStatement pst2 = dbconnection.prepareStatement("UPDATE product SET categoryID = ?, productName = ?, productDescription = ?,"
					+ " productColor = ? WHERE productID = ?");
			pst2.setInt(1, newcategoryID);
			pst2.setString(2, newproduct.getProductName());
			pst2.setString(3, newproduct.getProductDescription());
			pst2.setString(4, newproduct.getProductColor());
			pst2.setInt(5, oldproductID);
			
			
			//execute the query
			pst2.executeUpdate();
		} catch(SQLException ex2) {
			ex2.printStackTrace();
			return false;
		}
		return true;
	}
	
	//remove a product
	public boolean removeProduct(Connection dbconnection, Product product) {
		
		try {//for better code, you might want to first get the relevant category id before proceeding
			PreparedStatement pst = dbconnection.prepareStatement("DELETE FROM product WHERE productName=?"
					+ " AND productDescription = ?");
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDescription());
			pst.executeUpdate();
			 
			System.out.println("Okay, product deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nothing deleted");
			return false;
		}
		return true;
	}
	
	//add a new product
	public boolean addProduct(Connection dbconnection, Product product, int categoryID) {
		PreparedStatement pst;
		try {
			pst = dbconnection.prepareStatement("INSERT IGNORE INTO product(categoryID, productName, productDescription, productColor) VALUES(?,?,?,?)");
			
			
			pst.setInt(1, categoryID);
			pst.setString(2, product.getProductName());
			pst.setString(3, product.getProductDescription());
			pst.setString(4, product.getProductColor());
			
			
			//execute the query
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
