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
	UserResource user;
	
	public UserController(UserResource userResource) {
		this.user = userResource;
	}

	public Response createUser() throws SQLException {
		UserDB db = new UserDB();
		String location = db.createUser(this.user);
		if(location!="") {
			return Response.status(Response.Status.CREATED).entity(user).
				header("Location", location).header("Content-Location", location).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to create user").build();
	}
}
