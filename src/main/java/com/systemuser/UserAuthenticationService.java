package com.systemuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.ConnectionManager;

public class UserAuthenticationService {

	public boolean userExists(String userName, String userPassword) {
		Connection dbconnection = new ConnectionManager().createConnection();
		try {
			PreparedStatement pst = dbconnection
					.prepareStatement("SELECT userID FROM systemuser " + "WHERE userName = ? AND userPassword = ?");
			pst.setString(1, userName);
			pst.setString(2, userPassword);

			ResultSet rs = pst.executeQuery();

			if (rs.next())
				return true;
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// method to insert a new user
	public boolean signupUser(NewSystemUser systemuser) {
		Connection dbconnection = new ConnectionManager().createConnection();
		// if the current user does not exist, then add them
		try {
			PreparedStatement pst = dbconnection.prepareStatement(
					"INSERT INTO systemuser (userRealName, userName, " + "userPassword) VALUES(?,?,?)");

			pst.setString(1, systemuser.getUserRealName());
			pst.setString(2, systemuser.getUserName());
			pst.setString(3, systemuser.getUserPassword());

			pst.executeUpdate();
			dbconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;// return false if new user cannot be signed in
		}
		return true;// return value if a user is successfully signed up
	}
}
