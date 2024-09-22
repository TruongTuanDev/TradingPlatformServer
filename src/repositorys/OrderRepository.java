package repositorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.Wallet;

public class OrderRepository {
	static Connection con;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static Statement st = null;
	public Wallet saveOrder(Wallet wallet) {
		try {
			con = utils.ConnectDB.getConnection();
			String query = "INSERT INTO wallets (price,	quantity,quantity_curency,symbol,buy_date,account_id ) VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setDouble(1, wallet.getPrice());
			ps.setDouble(2, wallet.getQuantity());
			ps.setDouble(3, wallet.getQuantityCurency());
			ps.setString(4,wallet.getSymbol());
			ps.setString(5, wallet.getBuyDate());
			ps.setString(6, wallet.getAccountID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wallet;
	}
}
