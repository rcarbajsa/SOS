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
	private UserResource user;
	
	
	public UserController(UserResource userResource) {
		this.user = userResource;
	}

	public Response createUser() throws SQLException {
		UserDB db = new UserDB();
		int id = db.createUser(this.user);
		if(id>0) {
			String location = uriInfo.getAbsolutePath() + "/" + id;
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", location).header("Content-Location", location).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create user").build();
	}
}
