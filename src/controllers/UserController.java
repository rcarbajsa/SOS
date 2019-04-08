package controllers;
import database.*;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class UserController   {
	
	@Context
	private UriInfo uriInfo;
	
	public UserController() {}
	
	public Response getUser(String userId) throws SQLException {
		UserResource user = new UserResource(userId);
		UserDB db = new UserDB();
		ResultSet rs = db.getUser(user);
		rs.beforeFirst();
		System.out.println(rs != null);
		System.out.println(rs.next());
		if(rs != null && rs.next()) {
			System.out.println("HOLAsoy el primer:");
			System.out.println("HOLA: " + rs.getString("name"));
			int i = rs.getInt(1);
			System.out.println("HOLA " + rs.getString(1));
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", i).header("Content-Location", i).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to get user information").build();
	}

	public Response createUser(UserResource user) throws SQLException {
		UserDB db = new UserDB();
		
		ResultSet rs = db.createUser(user);
		if(rs != null && rs.next()) {
			user.setId(rs.getString(1));
			String location = /*uriInfo.getAbsolutePath() + "/" + */user.getId();
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", location).header("Content-Location", location).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create user").build();
	}
	
	public Response editUser(String userId, String name, String username) throws SQLException {
		UserResource user = new UserResource(userId, name, username);
		UserDB db = new UserDB();
		int i = db.editUser(user);
		if(i > 0) {
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", i).header("Content-Location", i).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create user").build();
	}
	
	public Response removeUser(String userId) throws SQLException {
		UserResource user = new UserResource(userId);
		UserDB db = new UserDB();
		int i = db.removeUser(user);
		if(i > 0) {
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", i).header("Content-Location", i).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create user").build();
	}
}
