package controllers;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import database.PostDB;
import resources.PostResource;
import resources.UserResource;


public class PostController extends Controller{
	
	public PostController(UriInfo uriInfo) {
		super(uriInfo);
	}
	
	public Response postMessage(PostResource post, String userId) throws SQLException {
		// Res stores body response
		HashMap<String, Object> res = new HashMap<String, Object>();
		
		// Check body data
		if (post.getContent() == null) {
			this.getBadRequestResponse(res, "Unable to post message: "
					+ "Content is not defined in the body of the post is undefined.");
		} else if (post.getContent().equals("")) {
			this.getBadRequestResponse(res, "Unable to post message: "
					+ "Content is of the post is empty.");
		}
		
		post.setUserId(userId);
		
		// Set data in DB
		PostDB db = new PostDB();
		ResultSet rs = db.createPost(post);
		
		if(rs.next()) {
			// Prepare data to send back to client
			post.setPostId(rs.getString(1));
			String location = this.getPath() + "/post/" + post.getPostId();
			return this.getCreatedResponse(res, post, location, "Post created succesfully");
		}
		
		// Error
		return this.getInternalServerErrorResponse(res, "There was an error. Unable to create a post");
	}
	
	public Response deleteMessage(String userId, String postId) throws SQLException {
		// Res stores body response
		HashMap<String, Object> res = new HashMap<String, Object>();
		
		PostResource post = new PostResource(postId, userId);
		
		// Set data in DB
		PostDB db = new PostDB();
		int affectedRows = db.deletePost(post);
		if(affectedRows > 0 ){
			// Prepare data to send back to client
			return this.getOkResponse(res, post, "Message deleted succesfully");
		}
		
		// Error
		return this.getInternalServerErrorResponse(res, "There was an error. Unable to delete message");
	}
	
	public Response editMessage(PostResource post, String postId, String userId)throws SQLException {
		// Res stores body response
		HashMap<String, Object> res = new HashMap<String, Object>();
		
		post.setPostId(postId);
		post.setUserId(userId);
		
		// Set data in DB
		PostDB db = new PostDB();
		int affectedRows = db.editPost(post);
		
		if(affectedRows > 0 ){
			// Prepare data to send back to client
			String location = this.getPath() + "/post/" + postId;
			return this.getCreatedResponse(res, post, location, "Post updated succesfully");
		}
		
		// Error
		return this.getInternalServerErrorResponse(res, "There was an error. Unable to edit message");
	}
	public Response getMessage( String userId)throws SQLException {
		// Res stores body response
		HashMap<String, Object> res = new HashMap<String, Object>();
		
		// Set data in DB
		PostDB db = new PostDB();
		ResultSet rs= db.getPost(userId);
		PostResource post= new PostResource();
		if(rs.next() ){
			// Prepare data to send back to client
			 post.setlocation("http://localhost:8080/SOS/api/post/" + rs.getString("post_id"));
			 post.setContent(rs.getString("content"));
			 post.setUserId(rs.getString("user_id"));
			 post.setPostId(rs.getString("post_id"));
			 post.setDate(rs.getString("created_at"));
			return this.getOkResponse(res, post, "Post loaded succesfully");
		}
		
		// Error
		return this.getInternalServerErrorResponse(res, "There was an error. Unable to get message");
	}
	public Response getMessageFriends( String userId, String content)throws SQLException {
		// Res stores body response
		HashMap<String, Object> res = new HashMap<String, Object>();
		
		// Set data in DB
		PostDB db = new PostDB();
		ResultSet rs= db.getPostFriends(userId,content);
		if(rs != null) {
			// Array with posts
			ArrayList<PostResource> posts = new ArrayList<PostResource>();
			while(rs.next()) {
				posts.add(new PostResource(
						rs.getString("post_id"),
						rs.getString("user_id"),
						rs.getString("content"),
						rs.getString("created_at"),
						"http://localhost:8080/SOS/api/user/"+rs.getString("user_id"),
						"http://localhost:8080/SOS/api/post/"+rs.getString("post_id")
						));
			}
			
			return this.getOkResponse(res, posts, "Data loaded succesfully");
		}
		
		// Error
		return this.getInternalServerErrorResponse(res, "Unable to get friends information");
	}
	
}
