package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Category;
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class CategoryDAO {

		private Connection conn;
		
		private Statement st;
		
		private PreparedStatement pst;
		
		private ResultSet rs;
		
		public List<Category> getCategories (){
			String sql = "SELECT id, name FROM categories ORDER BY id DESC";
			List<Category> categories = new ArrayList<>();
			conn = ConnectDBUtils.getConnection();
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Category category =new Category(rs.getInt("id"), rs.getString("name"));
					categories.add(category);
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDBUtils.close(st,rs,conn);
			}
			return categories;
		}
		
		public int add (Category cat){
			String sql = "INSERT INTO categories (name) VALUES(?)";
			int result=0;
			conn = ConnectDBUtils.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, cat.getName());
				result = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDBUtils.close(pst,conn);
			}
			return result;
		}
		public int del (int id){
			String sql = "DELETE FROM categories WHERE id=?";
			int result=0;
			conn = ConnectDBUtils.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDBUtils.close(pst,conn);
			}
			return result;
		}
		public Category getById (int id){
			String sql = "SELECT id,name FROM categories WHERE id= ?";
			Category category=null;
			conn = ConnectDBUtils.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next()) {
					 category =new Category(id, rs.getString("name"));
					
				}
				//result = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectDBUtils.close(pst,conn);
			}
			return category;
		}
		
		
	public static void main(String[] args) {
		CategoryDAO item=new CategoryDAO();
		Category cat=item.getById(13);
		System.out.println(cat.getName());
	}
	public int update (Category cat){
		String sql = "UPDATE categories SET name = ? WHERE id = ?";
		int result=0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
			pst.setInt(2, cat.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
}

	public int numberOfItemCat() {
		String sql = "SELECT COUNT(*) as count FROM categories   ";
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

	public List<Category> getCategoriesPanigation(int offset) {
		String sql = "SELECT id, name FROM categories ORDER BY id DESC LIMIT ?,?";
		List<Category> categories = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,offset);
			pst.setInt(2,DefineUtil.NUMBER_PER_PAGE);
			rs =pst.executeQuery();
			while (rs.next()) {
				Category category =new Category(rs.getInt("id"), rs.getString("name"));
				categories.add(category);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return categories;
	}

	public List<Category> getCategoriesSearch(String searchCategory) {
		String sql = "SELECT id,name FROM categories  WHERE 1 ";
		if(!"".equals(searchCategory)) {
			sql+= "AND name LIKE ?";
			
		}
		List<Category> cat = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(searchCategory)) {
				pst.setString(1, "%"+searchCategory+"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				Category item=new Category(id, name);

				cat.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return cat;
	}

	public boolean hasCategory(Category category) {
		String sql="SELECT * FROM categories WHERE name=?";
		conn=ConnectDBUtils.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, category.getName());
			rs=pst.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return false;
	}
}

