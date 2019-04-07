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
	@Context
	protected UriInfo uriInfo;

	protected DataSource ds;
	protected Connection conn;

	public UserDB() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/FaceSOS");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getUser(UserResource user) {
		if(this.conn != null) {
			
		}
		return -1;
	}
	
	public int createUser(UserResource user) throws SQLException {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/FaceSOS");
			this.conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(this.conn);
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
