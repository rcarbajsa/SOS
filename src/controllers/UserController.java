package controllers;
import database.*;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Response;

public class UserController   {
	public UserController() {}
	
	public Response getUser(String userId) throws SQLException {
		UserResource user = new UserResource(userId);
		UserDB db = new UserDB();
		int i = db.getUser(user);
		if(i > 0) {
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", i).header("Content-Location", i).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create user").build();
	}

	public Response createUser(UserResource user) throws SQLException {
		UserDB db = new UserDB();
		int location = db.createUser(user);
		if(location > 0) {
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
