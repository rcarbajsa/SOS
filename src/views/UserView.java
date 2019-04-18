package views;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import controllers.FriendController;
import controllers.UserController;
import resources.UserResource;

@Path("/user")
public class UserView {
  
	@Context
	private UriInfo uriInfo;

	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") String userId) throws SQLException {
		return new UserController(this.uriInfo).getUser(userId);
	}

	// TODO: Remove default Value in String name and check only with null
	@GET
	public Response getUsers(@QueryParam("limit-to") int limitTo, 
			@QueryParam("page") @DefaultValue("1") int page, @QueryParam("name") String name) 
					throws SQLException {
		return new UserController(this.uriInfo).getUsers(name, limitTo, page);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserResource user) throws SQLException {
		return new UserController(this.uriInfo).createUser(user);
	}

	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editUser(@PathParam("userId") String userId, UserResource user) throws SQLException {
		return new UserController(this.uriInfo).editUser(userId, user);
	}

	@DELETE
	@Path("/{userId}")
	public Response removeUser(@PathParam("userId") String userId) throws SQLException {
		return new UserController(this.uriInfo).removeUser(userId);
	}

	@GET
	@Path("{userId}/friends")
	public Response getFriends(@PathParam("userId") String userId, @QueryParam("page") @DefaultValue("1") int page,
			@QueryParam("name") String name, @QueryParam("limit-to") int limitTo)
			throws SQLException {
		return new FriendController(this.uriInfo).getFriends(userId, name, limitTo, page);
	}
	
	// Add friend
    @POST
    @Path("/{userId}/friends/{friendId}")
    // @Produces(MediaType.APPLICATION_JSON)
    public Response addFriend(@PathParam("userId") String userId, @PathParam("friendId") String friendId)
            throws SQLException {
        return new FriendController(this.uriInfo).addFriend(userId, friendId);
    }

    // Remove friend
    @DELETE
    @Path("/{userId}/friends/{friendId}")
    // @Produces(MediaType.APPLICATION_JSON)
    public Response removeFriend(@PathParam("userId") String idUser, @PathParam("friendId") String idFriend)
            throws SQLException {
        return new FriendController(this.uriInfo).removeFriend(idUser, idFriend);
    }
}
