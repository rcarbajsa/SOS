package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.naming.NamingContext;

public class Conexion {
	@Context
	protected UriInfo uriInfo;

	protected DataSource ds;
	 Connection conn;
	
	public Conexion() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/FaceSOS");
			this.conn = ds.getConnection();
			System.out.println("Conexion:  "+this.conn);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String args[]) {
//		Conexion a= new Conexion();
//	}

}
