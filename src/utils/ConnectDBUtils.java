package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBUtils {

	
	//public static final String URL ="jdbc:mysql://node239362-quoclv-bsong.j.layershift.co.uk/bsong?useUnicode=true&characterEncoding=UTF-8";
	
	//public static final String USERNAME ="root";
	
	//public static final String PASSWORD ="AKKmov19960";
	
	public static final String URL ="jdbc:mysql://localhost:3306/bsong?useUnicode=yes&characterEncoding=UTF-8";
	
	public static final String USERNAME ="root";
	
	public static final String PASSWORD ="";
	
	public static Connection getConnection() {
		
		Connection conn = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
						
						//lﾃ｡ﾂｺﾂ･y ra danh sﾃδ｡ch cﾃδ｡c danh mﾃ｡ﾂｻﾂ･c
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					
		return conn;
	}
	
	//overload: cﾃｹng tﾃｪn, khﾃ｡c tham s盻� truy盻］ vﾃ�o, cﾃｹng file
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public static void close(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pst) {
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement st, ResultSet rs, Connection conn) {
		close(st);
		close(rs);
		close(conn);
	}
	public static void close(PreparedStatement pst, ResultSet rs, Connection conn) {
		close(pst);
		close(rs);
		close(conn);
	}
	public static void close(PreparedStatement pst, Connection conn) {
		close(pst);
		close(conn);
	}
}
