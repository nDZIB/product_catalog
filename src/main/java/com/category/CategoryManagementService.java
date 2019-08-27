package com.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CategoryManagementService {

	// method to remove a category
	public boolean removeCategory(Connection dbconnection, Category category) {
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("DELETE FROM category WHERE categoryName=? AND categoryDescription=?");
			pst.setString(1, category.getCategoryName());
			pst.setString(2, category.getCategoryDescription());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// method to update (edit) a category
	public boolean editCategory(Connection dbconnection, Category oldCategory, Category newCategory) {
		try {
			PreparedStatement pst = dbconnection.prepareStatement("UPDATE category SET categoryName= ?, "
					+ "categoryDescription = ? WHERE categoryName=? AND categoryDescription= ?");

			pst.setString(1, newCategory.getCategoryName());
			pst.setString(2, newCategory.getCategoryDescription());
			pst.setString(3, oldCategory.getCategoryName());
			pst.setString(4, oldCategory.getCategoryDescription());

			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ADD A NEW CATEGORY
	public boolean addNewCategory(Connection dbconnection, Category newCategory) {
		try {
			PreparedStatement pst = dbconnection.prepareStatement(
					"INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)");

			// set the parameters
			pst.setString(1, newCategory.getCategoryName());
			pst.setString(2, newCategory.getCategoryDescription());
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// retrieve category id
	public int getCategoryID(Connection dbconnection, Category category) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		}
		return 0;
	}

	// add a new category and retrieve its id
	public int addCategory(Connection dbconnection, Category category) {
		try {
			PreparedStatement pst2 = dbconnection.prepareStatement(
					"INSERT IGNORE INTO category(categoryName, categoryDescription) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst2.setString(1, category.getCategoryName());
			pst2.setString(2, category.getCategoryDescription());

			pst2.executeUpdate();// i now have the id of the new category
			ResultSet rs2 = pst2.getGeneratedKeys();

			while (rs2.next())
				return (rs2.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// get all categories
	public List<Category> getAllCategories(Connection dbconnection) {
		List<Category> allCategories = new ArrayList<Category>();
		try {
			PreparedStatement pst = dbconnection.prepareStatement("SELECT categoryName, categoryDescription "
					+ "FROM category");
			
			ResultSet setOfCategories = pst.executeQuery();
			while(setOfCategories.next()) {
				Category newCategory = new Category(setOfCategories.getString(1), setOfCategories.getString(2));
				
				allCategories.add(newCategory);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Unable to retrieve list of products");
		}
		return allCategories;
	}
}
