package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "post")

public class PostResource {
	private int postId;
	private UserResource user;
	private String content;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    private String location;

	public PostResource() {
	}
	public PostResource(int id, ResultSet rs, String domain) throws SQLException {
		  this.postId = id;
		  this.content = rs.getString("content");
		  this.location = domain + "post/" + this.getPostId();
//	      this.createdAt = rs.getTimestamp("created_at");
	      this.updatedAt = rs.getTimestamp("updated_at");
		}

	public PostResource(String postId, String userId) {
		this.postId = Integer.parseInt(postId);
		this.user = new UserResource(userId);
	}

	public PostResource(ResultSet rs, String baseUri) throws SQLException {
	  this.postId = rs.getInt("post_id");
	  this.content = rs.getString("content");
//	  this.createdAt = rs.getTimestamp("created_at");
	  this.updatedAt = rs.getTimestamp("updated_at");
	  this.location = baseUri + "user/" + rs.getInt("user_id") + "/post/" + this.postId;
	}

	public PostResource(String content) {
		this.content = content;
	}
	public PostResource(int postId) {
		this.postId = postId;
	}
	
	
	
	@XmlAttribute(required=false)
	public int getPostId() {
		return this.postId;
	}

	public UserResource getUser() {
		return this.user;
	}

	public String getContent() {
		return this.content;
	}
	
	public Timestamp getCreatedAt() {
      return this.createdAt;
  }
	
	public Timestamp getUpdatedAt() {
      return this.updatedAt;
    }
	
	public String getLocation() {
	  return this.location;
	}

	public void setPostId(String postId) {
		this.postId = Integer.parseInt(postId);
	}
	public void setPostId(int postId) {
		this.postId = (postId);
	}
	
	public void setUser(UserResource user) {
		this.user = user;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
      this.updatedAt = updatedAt;
    }
	
	public void setLocation(String location) {
	  this.location = location;
	}
}
