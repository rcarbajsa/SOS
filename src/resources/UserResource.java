package resources;
import resources.PostResource;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;

public class UserResource {
	private int id;
	private String username;
	private String name;
	
	public UserResource(String name, String username) {
		this.name = name;
		this.username = username;
	}

	@XmlAttribute(required=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
}
