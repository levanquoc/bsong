package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import model.User;
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class UserDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	public ArrayList<User> getItems() {
		ArrayList<User> items = new ArrayList<>();
		String sql = "SELECT id,username,password,fullname FROM `users` ORDER BY id DESC";
		conn = ConnectDBUtils.getConnection();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");

				User user = new User(id, username, password, fullname);
				items.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return items;
	}

	public int addItem(User user) {
		int result = 0;
		String sql = "INSERT INTO users( username, password,fullname) VALUES (?,?,?)";
		conn = ConnectDBUtils.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return result;
	}

	public boolean hasUser(String username) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM users WHERE username =?";
		conn = ConnectDBUtils.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return false;
	}

	public User getByID(int id) {
		// TODO Auto-generated method stub
		User user = null;
		String sql = "SELECT id,username,password,fullname FROM users WHERE id =?";
		conn = ConnectDBUtils.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(id, rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return user;
	}

	public int update(User user) {
		String sql = "UPDATE users SET username=?,password=?,fullname=? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setInt(4, user.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst, conn);
		}
		return result;
	}


public int delItem(int id) {
	String sql = "DELETE FROM users WHERE id=?";
	int result = 0;
	conn = ConnectDBUtils.getConnection();
	try {
		pst=conn.prepareStatement(sql);
		pst.setInt(1, id);
		result = pst.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		ConnectDBUtils.close(pst, conn);
	}
	return result;
}

public boolean delAdmin(int id) {
	boolean Check=false;
	String sql = "SELECT id,username,password,fullname FROM users WHERE id =?";
	conn = ConnectDBUtils.getConnection();

	try {
		pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next()) {
			String username=rs.getString("username");
			if("admin".equals(username)) {
				Check=true;
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		ConnectDBUtils.close(st, rs, conn);
	}
	return Check;
}

public User findByUserAndPassword(String username, String password) {
	
	User user = null;
	String sql = "SELECT id,username,password,fullname FROM users WHERE username=? AND password=?";
	conn = ConnectDBUtils.getConnection();

	try {
		pst = conn.prepareStatement(sql);
		pst.setString(1, username);
		pst.setString(2, password);
		rs = pst.executeQuery();
		if (rs.next()) {
			user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		ConnectDBUtils.close(st, rs, conn);
	}
	return user;
}

public int getnumberOfItemUser() {
	String sql = "SELECT COUNT(*) as count FROM users   ";
	conn = ConnectDBUtils.getConnection();
	try {
		st = conn.createStatement();
		rs=st.executeQuery(sql);
		
		while (rs.next()) {
			int count=rs.getInt("count");
			return count;
		}
			
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		ConnectDBUtils.close(st,rs,conn);
	}
	
	return 0;
	
}

public ArrayList<User> getItemsPanigation(int offset) {
	ArrayList<User> items = new ArrayList<>();
	String sql = "SELECT id,username,password,fullname FROM `users` ORDER BY id DESC LIMIT ?,?";
	conn = ConnectDBUtils.getConnection();
	
	try {
		pst = conn.prepareStatement(sql);
		pst.setInt(1,offset);
		pst.setInt(2,DefineUtil.NUMBER_PER_PAGE);
		rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String fullname = rs.getString("fullname");

			User user = new User(id, username, password, fullname);
			items.add(user);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		ConnectDBUtils.close(st, rs, conn);
	}
	return items;
}

public ArrayList<User> getSearchname(String searchname) {
	String sql = "SELECT id,username,password,fullname FROM users  WHERE 1 ";
	if(!"".equals(searchname)) {
		sql+= "AND username LIKE ?";
		
	}
	ArrayList<User> user = new ArrayList<>();
	conn = ConnectDBUtils.getConnection();
	try {
		pst = conn.prepareStatement(sql);
		if(!"".equals(searchname)) {
			pst.setString(1, "%"+searchname+"%");
		}
		rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String fullname = rs.getString("fullname");
			User item=new User(id, username, password, fullname);
			user.add(item);
		}
			
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		ConnectDBUtils.close(st,rs,conn);
	}
	return user;
}
}
