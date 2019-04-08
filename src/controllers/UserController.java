package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import database.UserDB;
import resources.UserResource;

public class UserController   {
	private UriInfo uriInfo;
	
	public UserController(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	
	public Response getUser(String userId) throws SQLException {
		UserResource user = new UserResource(userId);
		UserDB db = new UserDB();
		ResultSet rs = db.getUser(user);
		if(rs != null && rs.next()) {
			System.out.println();
			user.setName(rs.getString("name"));
			user.setUsername(rs.getString("username"));
			String location = this.uriInfo.getAbsolutePath() + "/user/" + userId;
			return Response
					.status(Response.Status.CREATED)
					.entity(user)
					.header("Location", location)
					.header("Content-Location", location)
					.build();
		}
		// Error message should be an object
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to get user information")
				.build();
	}

	public Response createUser(UserResource user) throws SQLException {
		UserDB db = new UserDB();
		ResultSet rs = db.createUser(user);
		if(rs != null && rs.next()) {
			user.setId(rs.getString(1));
			String location = this.uriInfo.getAbsolutePath() + "/user/" + user.getId();
			return Response
					.status(Response.Status.CREATED)
					.entity(user)
					.header("Location", location)
					.header("Content-Location", location)
					.build();
		}
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to create user")
				.build();
	}
	
	public Response editUser(String userId, UserResource user) throws SQLException {
		user.setId(userId);
		UserDB db = new UserDB();
		ResultSet rs = db.editUser(user);
		System.out.println(rs.next());
		if(rs.next()) {
			System.out.println("aaaaaaa");
			String location = this.uriInfo.getAbsolutePath() + "/user/" + user.getId();
			return Response
					// TODO check Status.ACCEPTED ???
					.status(Response.Status.ACCEPTED)
					.entity(user)
					.header("Location", location)
					.header("Content-Location", location)
					.build();
		}
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to create user")
				.build();
	}
	
	public Response removeUser(String userId) throws SQLException {
		UserResource user = new UserResource(userId);
		UserDB db = new UserDB();
		ResultSet rs = db.removeUser(user);
		if(rs != null && rs.next()) {
			String location = this.uriInfo.getAbsolutePath() + "/user/" + user.getId();
			return Response
					// TODO check Status.ACCEPTED ???
					.status(Response.Status.ACCEPTED)
					.entity(user)
					.header("Location", location)
					.header("Content-Location", location)
					.build();
		}
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to create user")
				.build();
	}
	
	public Response getFriends(String name,int count,String userId) throws SQLException {
		//UserResource user = new UserResource(userId);
		HashMap <String,Object> rs = new HashMap <String,Object>();
		UserDB db = new UserDB();
		ResultSet resultSet = db.getFriends(name,count,userId);
		try {
			ArrayList<UserResource> list= new ArrayList<UserResource>();
			while(resultSet.next()) {
				UserResource user = new UserResource();
				user.setName(resultSet.getString("name"));
				user.setId(resultSet.getString("friend_id"));
				user.setUsername(resultSet.getString("username"));
				list.add(user);
			}
			rs.put("data", list);
			rs.put("n_amigos",list.size());
			return Response
				.status(Response.Status.CREATED)
				.entity(rs)
				.build();
		
		}
		catch(SQLException e){
		// Error message should be an object
			return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to get user information")
				.build();
		}
	}

	
}
