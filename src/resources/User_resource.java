package resources;
import resources.Post;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

public class User_resource {
	private int id;
	private String username;
	private String name;
	private ArrayList<Post> posts;
	private ArrayList<User_resource> user_resources;
	
	public User_resource(String username, String name) {
		this.username=username;
		this.name=name;
		this.posts=new ArrayList<Post>();
		this.user_resources=new ArrayList<User_resource>();
	}
	
	@XmlAttribute(required=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
}
