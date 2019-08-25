package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
					"INSERT IGNORE INTO category(categoryName, " + "categoryDescription) VALUES(?,?)");

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
			while (rs.next()) {
				return (rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
}
