package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Songs;
import utils.ConnectDBUtils;
import utils.DefineUtil;

public class SongsDao {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	public List<Songs> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id ORDER BY s.id";
		List<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
	}
	public List<Songs> getAll(int number) {
		// TODO Auto-generated method stub
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id ORDER BY s.id LIMIT ?";
		List<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,number);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
	}
	public int add(Songs song) {
		int result=0;
		String sql = "INSERT INTO songs( name, preview_text, detail_text,picture,cat_id ) VALUES (?,?,?,?,?)";
		
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,song.getName());
			pst.setString(2,song.getPreview_text());
			pst.setString(3,song.getDetail_text());
			pst.setString(4,song.getPicture());
			pst.setInt(5,song.getCategory().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return result;
	}
	public int del (int id){
		String sql = "DELETE FROM songs WHERE id=?";
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
	public Songs getById(int id) {
		Songs song=null;
		String sql = "SELECT id, name, preview_text, detail_text, date_create, cat_id, picture, counter FROM songs WHERE id=?";
		
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				 song =new Songs(id,rs.getString("name"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getString("picture") ,new Category(rs.getInt("cat_id")));
				
			}
			//result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return song;
	}
	public int update(Songs song) {
		String sql = "UPDATE songs SET name=?,preview_text=?,detail_text=?,cat_id=?,picture=? WHERE id=?";
		int result = 0;
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3,song.getDetail_text());
			pst.setInt(4, song.getCategory().getId());
			pst.setString(5,song.getPicture());
			pst.setInt(6, song.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst, conn);
		}
		return result;

	}
	public ArrayList<Songs> getByCategory(int cat_id) {
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id  WHERE cat_id=?";
		ArrayList<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,cat_id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
		
	}
	public Songs getSongdetailById(int id) {
		Songs song=null;
		String sql = "SELECT id, name, preview_text, detail_text, date_create, cat_id, picture, counter FROM songs WHERE id=?";
		
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				 song =new Songs(id, rs.getString("name"),rs.getString("preview_text") ,rs.getString("detail_text") , rs.getTimestamp("date_create"), rs.getString("picture"),rs.getInt("counter"), new Category(rs.getInt("cat_id")));
				
			}
			//result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst,conn);
		}
		return song;
	}
	public ArrayList<Songs> getSongRelation(Songs songdetail,int number) {
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id  WHERE cat_id =? && s.id !=? ORDER BY id DESC LIMIT ?";
		ArrayList<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,songdetail.getCategory().getId());
			pst.setInt(2,songdetail.getId());
			pst.setInt(3,number);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("s.id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
	}
	public void increaseView(int id) {
		String sql = "UPDATE songs SET counter=counter+1 WHERE id=?";
		
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(pst, conn);
		}
		
		
	}
	public int numberOfItems() {
		String sql = "SELECT COUNT(*) as count FROM songs   ";
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
	public List<Songs> getItemPanigation(int offset) {
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id ORDER BY s.id DESC LIMIT ?,?";
		List<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,offset);
			pst.setInt(2,DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
		
	}
	public int getNumberofItemDetail(int id) {
		String sql = "SELECT COUNT(*) as count FROM songs WHERE cat_id=?  ";
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			rs=pst.executeQuery();
			
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
	public ArrayList<Songs> getByCategoryofPageDetail(int offset,int catID) {
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id WHERE cat_id=? ORDER BY s.id LIMIT ?,?";
		ArrayList<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,catID);
			pst.setInt(2,offset);
			pst.setInt(3,DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
		
	}
	public List<Songs> getSearchName(String searchname) {
		String sql = "SELECT s.*,c.name as catName FROM songs AS s INNER JOIN categories as c ON c.id=s.cat_id WHERE 1 ";
		if(!"".equals(searchname)) {
			sql+= "AND s.name LIKE ?";
			
		}
		ArrayList<Songs> songs = new ArrayList<>();
		conn = ConnectDBUtils.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(searchname)) {
				pst.setString(1, "%"+searchname+"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String preview_text = rs.getString("preview_text");
				String detail_text= rs.getString("detail_text");
				Timestamp date_create=rs.getTimestamp("date_create");
				int catId = rs.getInt("cat_id");
				String picture= rs.getString("picture");
				int counter = rs.getInt("counter");
				String catName=rs.getString("catName");

				Songs item = new Songs(id,name, preview_text, detail_text, date_create, picture, counter,new Category(catId,catName));
				songs.add(item);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtils.close(st,rs,conn);
		}
		return songs;
	}


}
