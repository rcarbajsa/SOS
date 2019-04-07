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
	
	public int getUser(UserResource user) {
		if(this.conn != null) {
			
		}
		return -1;
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
