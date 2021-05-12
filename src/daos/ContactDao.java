package daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Contact;
import utils.ConnectDBUtils;
import utils.DefineUtil;
public class ContactDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	public int addItem(Contact contact) {
		int result =0;
		String sql = "INSERT INTO contacts( name,email,website,message) VALUES (?,?,?,?)";
		conn = ConnectDBUtils.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,contact.getName() );
			pst.setString(2,contact.getEmail() );
			pst.setString(3,contact.getWebsite());
			pst.setString(4,contact.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return result;
	}
	public List<Contact> getContact() {
		String sql = "SELECT id, name,email,website,message FROM contacts ORDER BY id DESC";
		List<Contact> contact= new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact contactItem=new Contact(rs.getInt("id"),rs.getString("name"),rs.getString("email") ,rs.getString("website"), rs.getString("message"));
				contact.add(contactItem);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return contact;
	}
	public int del(int id) {
		String sql = "DELETE FROM contacts WHERE id=?";
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
	public int numberOfItem() {
		String sql = "SELECT COUNT(*) as count FROM contacts   ";
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
	public List<Contact> getContactPanagition(int offset) {
		List<Contact> items = new ArrayList<>();
		String sql = "SELECT id,name,email,website,message FROM contacts ORDER BY id DESC LIMIT ?,?";
		conn = ConnectDBUtils.getConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,offset);
			pst.setInt(2,DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");

				Contact contact = new Contact(id, name, email, website, message);
				items.add(contact);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st, rs, conn);
		}
		return items;
	}
	public List<Contact> getSearchContact(String searchContact) {
		String sql = "SELECT id,name,email,website,message FROM contacts  WHERE 1 ";
		if(!"".equals(searchContact)) {
			sql+= "AND name LIKE ?";
			
		}
		List<Contact> contact = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(searchContact)) {
				pst.setString(1, "%"+searchContact+"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				Contact item=new Contact(id, name, email, website, message);
				contact.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,rs,conn);
		}
		return contact;
	}
}
