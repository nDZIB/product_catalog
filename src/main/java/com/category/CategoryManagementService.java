package com.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.connection.ConnectionManager;


public class CategoryManagementService {

	// method to remove a category
	public boolean removeCategory(Category category) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("DELETE FROM category WHERE categoryName=? AND categoryDescription=?");
			pst.setString(1, category.getCategoryName());
			pst.setString(2, category.getCategoryDescription());
			pst.execute();

			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// method to update (edit) a category
	public boolean editCategory(Category oldCategory, Category newCategory) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection.prepareStatement("UPDATE category SET categoryName= ?, "
					+ "categoryDescription = ? WHERE categoryName=? AND categoryDescription= ?");

			pst.setString(1, newCategory.getCategoryName());
			pst.setString(2, newCategory.getCategoryDescription());
			pst.setString(3, oldCategory.getCategoryName());
			pst.setString(4, oldCategory.getCategoryDescription());

			pst.execute();
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ADD A NEW CATEGORY
	public boolean addNewCategory(Category newCategory) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection.prepareStatement(
					"INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)");

			// set the parameters
			pst.setString(1, newCategory.getCategoryName());
			pst.setString(2, newCategory.getCategoryDescription());
			pst.execute();

			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// retrieve category id
	public int getCategoryID(Category category) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection.prepareStatement(
					"SELECT categoryID FROM category WHERE categoryName = ? AND " + "categoryDescription = ? ");
			// you want to edit this query to include other fields
			pst.setString(1, category.getCategoryName());
			pst.setString(2, category.getCategoryDescription());

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return (rs.getInt(1));
			}
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	// add a new category and retrieve its id
	public int addCategory(Category category) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst2 = dbconnection.prepareStatement(
					"INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst2.setString(1, category.getCategoryName());
			pst2.setString(2, category.getCategoryDescription());

			pst2.executeUpdate();// i now have the id of the new category
			ResultSet rs2 = pst2.getGeneratedKeys();

			while (rs2.next()) {
				return (rs2.getInt(1));
			}
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
