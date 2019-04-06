package resources;
import resources.Post;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

public class User_resource {
	private int id;
	private String username;
	private String name;
	private String surname1;
	private String surname2;
	private ArrayList<Post> posts;
	private ArrayList<User_resource> user_resources;
	
	public User_resource(String username, String name, String surname1,String surname2) {
		this.username=username;
		this.surname1=surname1;
		this.surname2=surname2;
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
