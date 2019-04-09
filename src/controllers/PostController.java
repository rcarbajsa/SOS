package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import database.PostDB;
import resources.PostResource;
public class PostController {
	UriInfo uri;
	public PostController(UriInfo uri) {
		this.uri = uri;
	}
	
	public Response postMessage(String user_id,PostResource post) throws SQLException {
		// Res stores body response
				HashMap<String, Object> res = new HashMap<String, Object>();
				
				// Check body data
				if (post.getContent() == null ) {
					res.put("message", "Unable to post message: "
							+ "Content of the message is empty.");
					return Response
							.status(Response.Status.BAD_REQUEST)
							.entity(res)
							.build();
				}
				
				// Set data in DB
				PostDB db = new PostDB();
				ResultSet rs = db.postMessage(user_id,post);
				
				if(rs.next()) {
					
					// Prepare data to send back to client
					post.setId(rs.getString(1));
					post.setUserId(user_id);
					res.put("message", "Message posted succesfully");
					res.put("data", post);
					String location = this.uri.getAbsolutePath() + "/post/" + post.getId();
					
					return Response
							.status(Response.Status.CREATED)
							.entity(res)
							.header("Location", location)
							.header("Content-Location", location)
							.build();
				}
				
				// Error
				return this.setMessage(res, "There was an error. Unable to post message");
			
	}
	public Response deleteMessage(String user_id,String post_id)throws SQLException {
		// Res stores body response
				HashMap<String, Object> res = new HashMap<String, Object>();
				// Set data in DB
				PostDB db = new PostDB();
				int affectedRows = db.deleteMessage(user_id,post_id);
				if(affectedRows > 0 ){
					// Prepare data to send back to client
					res.put("message", "Message deleted succesfully");
					String location = this.uri.getAbsolutePath() + "/post/" + post_id;
					return Response
							.status(Response.Status.CREATED)
							.entity(res)
							.header("Location", location)
							.header("Content-Location", location)
							.build();
				}
				
				// Error
				return this.setMessage(res, "There was an error. Unable to delete message");
			
	}
	public Response editMessage(String user_id,String post_id,PostResource post)throws SQLException {
		// Res stores body response
				HashMap<String, Object> res = new HashMap<String, Object>();
				// Set data in DB
				PostDB db = new PostDB();
				int affectedRows = db.editMessage(user_id,post_id,post);
				if(affectedRows > 0 ){
					// Prepare data to send back to client
					res.put("message", "Message updated succesfully");
					res.put("data", post);
					String location = this.uri.getAbsolutePath() + "/post/" + post_id;
					return Response
							.status(Response.Status.CREATED)
							.entity(res)
							.header("Location", location)
							.header("Content-Location", location)
							.build();
				}
				
				// Error
				return this.setMessage(res, "There was an error. Unable to edit message");
			
	}
	
	private Response setMessage(HashMap<String, Object> res, String err) {
		res.put("message", err);
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(res)
				.build();
	}
	

}
