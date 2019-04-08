package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import database.FriendDB;
import database.UserDB;
import resources.UserResource;

public class FriendController {
	private UriInfo uriInfo;

	public FriendController(UriInfo uriInfo) {
		this.uriInfo=uriInfo;
	}
	
	public Response addFriend(String idUser,String idFriend) throws SQLException {
		
		FriendDB db = new FriendDB();
		if(!idUser.equals(idFriend) && !db.checkFriends(idUser, idFriend)) {
			UserResource user=new UserResource(idFriend);
			UserDB db_get = new UserDB();
			ResultSet rs = db_get.getUser(user);
			db.addFriend(idUser,idFriend);
			if(rs != null && rs.next()) {
				System.out.println();
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				String location = "http://localhost:8080/SOS/api/user/" + idFriend;
				return Response
					.status(Response.Status.CREATED)
					.entity(user)
					.header("Location", location)
					.header("Content-Location", location)
					.build();
		}
		}
		// Error message should be an object
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to send friend request ")
				.build();
	
	}
public Response removeFriend(String idUser,String idFriend) throws SQLException {
		FriendDB db = new FriendDB();
		if(!idUser.equals(idFriend) && db.checkFriends(idUser, idFriend)) {	
			db.removeFriend(idUser,idFriend);
			String location = "http://localhost:8080/SOS/api/user/" + idFriend;
			return Response
					.status(Response.Status.CREATED)
					.entity(location)
					.header("Location", location)
					.header("Content-Location", location)
					.build();
		}
	
		// Error message should be an object
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Unable to remove friend")
				.build();
	
	}
}
