package database;
import resources.PostResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostDB extends Conexion{
	
	public PostDB() {
		super();
	}
	
	public ResultSet postMessage(String user_id,PostResource post) throws SQLException {
		// TODO: unique username value. If already exists, then error
		if(this.conn != null) {
			String query = "INSERT INTO `faceSOS`.`posts`(user_id,content) VALUE (?,?);";
			PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user_id);
			ps.setString(2, post.getContent());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			return rs;
		}
		return null;
	}
	public int deleteMessage(String user_id,String post_id) throws SQLException {
		// TODO: unique username value. If already exists, then error
		if(this.conn != null) {
			String query = "DELETE FROM`faceSOS`.`posts` WHERE user_id = ? AND post_id = ?;";
			PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user_id);
			ps.setString(2, post_id);
			int res=ps.executeUpdate();
			return res;
		}
		return -1;
	}
	public int editMessage(String user_id,String post_id,PostResource post) throws SQLException {
		// TODO: unique username value. If already exists, then error
		if(this.conn != null) {
			System.out.println("Post: "+ post_id+" User: "+user_id);
			String query = "UPDATE `faceSOS`.`posts` SET content = ?  WHERE user_id = ? AND post_id = ?;";
			PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, post.getContent());
			ps.setString(2, user_id);
			ps.setString(3, post_id);
			int res=ps.executeUpdate();
			return res;
		}
		return -1;
	}

}
