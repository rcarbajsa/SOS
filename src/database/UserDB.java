package database;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.naming.NamingContext;

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
	
	public int createUser(UserResource user) throws SQLException {
		String query = "INSERT INTO FaceSOS.users(name,username) VALUE ('"+user.getName()+"','"+user.getUsername()+"');";
		PreparedStatement ps = this.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			int id = rs.getInt(1);
			return id;
		}
		else {
			return -1;
		}	
	}
}
