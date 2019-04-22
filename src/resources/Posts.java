package resources;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import resources.PostResource;

@XmlRootElement(name = "posts")

public class Posts {
	private ArrayList<PostResource> posts;
	public Posts(){
		this.posts = new ArrayList<PostResource>();
	}
	@XmlElement(name="post") 
	public ArrayList<PostResource> getPosts(){
		return this.posts;
	}
}
