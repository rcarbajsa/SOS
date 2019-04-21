package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import resources.PostResource;
import resources.UserResource;

public class PostDB extends Conexion {

	public PostDB() {
		super();
	}

	public ResultSet createPost(PostResource post) throws SQLException {
		if (this.conn == null) 
		    return null;
		
		String query = "INSERT INTO posts (user_id,content) VALUE (?,?);";
		PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, post.getUser().getId());
		ps.setString(2, post.getContent());
		ps.executeUpdate();
		return ps.getGeneratedKeys();
	}

	public int deletePost(PostResource post) throws SQLException {
	    if (this.conn == null) 
            return -1;
		
		String query = "DELETE FROM posts WHERE post_id = ? AND user_id = ?;";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setInt(1, post.getUser().getId());
		ps.setInt(2, post.getPostId());
		return ps.executeUpdate();
	}

	public int editPost(PostResource post) throws SQLException {
	    if (this.conn == null) 
            return -1;
	    
		String query = "UPDATE posts SET content = ?  WHERE post_id = ? AND user_id = ?;";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(1, post.getContent());
		ps.setInt(2, post.getPostId());
		ps.setInt(3, post.getUser().getId());
		return ps.executeUpdate();
	}

	public ResultSet getPosts(UserResource user, int limitTo, int page, Date date) throws SQLException {
	    if (this.conn == null) 
            return null;
	    
		String query = "SELECT * FROM posts WHERE user_id = ? ";
		if (date != null)
			query+="AND updated_at BETWEEN ? AND ?";
		query+="ORDER BY updated_at DESC LIMIT ?,?;";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setInt(1, user.getId());
		int i = 2;
		if (date !=null) {
			Timestamp ts1 = new Timestamp(date.getTime());
			System.out.println("date1:"+ts1);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE,1);
			date = cal.getTime();
			ps.setTimestamp(i, ts1);
			i++;
			Timestamp ts2 = new Timestamp(date.getTime());
			System.out.println("date2:"+ts2);
			ps.setTimestamp(i, ts2);
			i++;
		}
		ps.setInt(i, page * limitTo);
		ps.setInt(i+1, page * limitTo + limitTo);
        System.out.println("pg " + page + " li" + limitTo);
		return ps.executeQuery();
	}

	public ResultSet getPostFriends(UserResource user, String content, int limitTo, int page) throws SQLException {
		if (this.conn != null) {
			String query = "SELECT * FROM posts JOIN friends ON (posts.user_id = friends.user2_id) WHERE friends.user1_id= ?";
			query += content != null ? " AND posts.content LIKE ?" : "";
			query += "LIMIT ?,?;";
			PreparedStatement ps = this.conn.prepareStatement(query);
			ps.setInt(1, user.getId());
			int i=2;
			if (content != null) {
				ps.setString(i, "%" + content + "%");
				i++;
			}
			ps.setInt(i, page * limitTo);
			ps.setInt(i+1, page * limitTo + limitTo);
			return ps.executeQuery();
		}
		return null;
	}
}
