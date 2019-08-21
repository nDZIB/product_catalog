package systemuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserValidation {

	public boolean userExists(Connection dbconnection, String userName, String userPassword) {
		try {
			PreparedStatement pst = dbconnection.prepareStatement("SELECT userID FROM systemuser "
					+ "WHERE userName = ? AND userPassword = ?");
			pst.setString(1, userName);
			pst.setString(2, userPassword);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
