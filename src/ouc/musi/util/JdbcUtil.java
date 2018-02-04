package ouc.musi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

	private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost/musi";
	private static String DB_USER_NAME = "musi";
	private static String DB_USER_PASSWORD = "musi";
	
	public static Connection conn;

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e1) {
			System.out.println("cannot find com.mysql.jdbc.Driver!");
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
		} catch (SQLException e2) {
			System.out.println("error on connecting to mysql");
			e2.printStackTrace();
		}
	}

//	public static void main(String args[]) throws SQLException {
//		Statement ste = conn.createStatement();
//		ResultSet rs = ste.executeQuery("select * from audit_music");
//		while (rs.next()) {
//			System.out.println(rs.getString(1));
//		}
//	}

}
