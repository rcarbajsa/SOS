package resources;

public class PostResource {
	private int id;
	private int user_id;
	private String content;
	
	public PostResource(String content) {
		this.content=content;
	}
	public PostResource() {
		
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public void setUserId(String id) {
		this.user_id=Integer.parseInt(id);
	}
	public void setId(String id) {
		this.id=Integer.parseInt(id);
	}
	public int getUserId() {
		return this.user_id;
	}
	public int getId() {
		return this.id;
	}
}
