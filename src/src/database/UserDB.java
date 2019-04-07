package database;
import resources.*;
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

public class UserDB extends Conexion {
	public UserDB() {
		super();
	}
	
	public ResultSet getUser(UserResource user) throws SQLException {
		if(this.conn != null) {
			String query = "SELECT name FROM `faceSOS`.`users` WHERE `user_id` = ?;";
			PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Integer.parseInt(user.getId()));
			ps.executeQuery();
			ResultSet rs = ps.getGeneratedKeys();
			System.out.println("GETEaaaT");
			return rs;
		}
		return null;
	}
	
	public ResultSet createUser(UserResource user) throws SQLException {
		if(this.conn != null) {
			String query = "INSERT INTO faceSOS.users(name,username) VALUE ('"+user.getName()+"','"+user.getUsername()+"');";
			PreparedStatement ps = this.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			return rs;
		}
		return null;
	}
	
	public int editUser(UserResource user) throws SQLException {
		if(this.conn != null) {
		}
		return -1;
	}
	
	public int removeUser(UserResource user) throws SQLException {
		if(this.conn != null) {
		}
		return -1;
	}
}
