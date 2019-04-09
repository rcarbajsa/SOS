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

import controllers.PostController;
import resources.PostResource;
@Path("user/{user_id}/post")
public class PostView {
	@Context
	private UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postMessage(@PathParam("user_id") String user_id ,PostResource post) throws SQLException {
		return new PostController(this.uriInfo).postMessage(user_id,post);
	}
	@Path("{post_id}")
	@DELETE
	public Response deleteMessage(@PathParam("user_id") String user_id ,@PathParam("post_id") String post_id ) throws SQLException {
		return new PostController(this.uriInfo).deleteMessage(user_id,post_id);
	}

	@Path("{post_id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editMessage(@PathParam("user_id") String user_id , @PathParam("post_id") String post_id ,PostResource post) throws SQLException {
		return new PostController(this.uriInfo).editMessage(user_id,post_id,post);
	}
}
