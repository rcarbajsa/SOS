package database;
import resources.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB extends Conexion {
	public UserDB() {
		super();
	}
	
	public int getUser(UserResource user) {
		if(this.conn != null) {
			
		}
		return -1;
	}
	
	public int createUser(UserResource user) throws SQLException {
		if(this.conn != null)
			return -1;
		
		String query = "Insert into faceSOS.users(name,username) value (?,?);";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(0, user.getName());
		ps.setString(1, user.getUsername());
		ps.executeQuery();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			//int id = rs.getInt(1);
			//user.setId(id);
			return rs.getInt(1);
		}
		return -1;
		
	}
	
	public int editUser(UserResource user) {
		if(this.conn != null) {
			
		}
		return -1;
	}
	
	public int removeUser(UserResource user) {
		if(this.conn != null) {
			
		}
		return -1;
	}
}
