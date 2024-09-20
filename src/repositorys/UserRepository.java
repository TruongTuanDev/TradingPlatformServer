package repositorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.User;

public class UserRepository {
	static Connection con;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static Statement st = null;
	public User save(User user) {
		try {
			LocalDate nDate = LocalDate.now();
			String date = nDate + "";
			con = utils.ConnectDB.getConnection();
			String query = "INSERT INTO accounts (username,password,email) VALUES (?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
    }
	public boolean checklogin(String username, String password) {
	    boolean isValid = false;
	    try {
	        con = utils.ConnectDB.getConnection();
	        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, username);
	        ps.setString(2, password);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            isValid = true; 
	            System.out.println(isValid);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isValid;
	}

}
