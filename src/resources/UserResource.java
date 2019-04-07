package resources;
import resources.PostResource;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

public class UserResource {
	private int id;
	private String username;
	private String name;
	private ArrayList<PostResource> posts;
	private ArrayList<UserResource> friends;
	
	
	public UserResource(String username, String name) {
		this.username = username;
		this.name = name;
		this.posts=new ArrayList<PostResource>();
		this.friends=new ArrayList<UserResource>();
		
	}
	
	@XmlAttribute(required=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
}
