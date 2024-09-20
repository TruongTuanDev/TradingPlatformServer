package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection getConnection(){
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tradingplatform","root",null);
		return con;	
	}catch(Exception e) {
		e.printStackTrace();
	}
		return null;
	}		
}
