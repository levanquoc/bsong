package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
	private static String url = "jdbc:mysql://node239362-quoclv-bsong.j.layershift.co.uk/bsong?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "AKKmov19960";
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Khﾃｴng th盻� n蘯｡p driver");
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(DBConnectionUtil.getConnection());
	}
}
