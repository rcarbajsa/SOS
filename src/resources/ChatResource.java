package resources;

public class ChatResource {
	private int senderUserId;
	private int receiverUserId;
	private String content;
	
	public ChatResource() {}
	
	public ChatResource(String senderUserId, String receiverUserId, String content) {
		this.senderUserId = Integer.parseInt(senderUserId);
		this.receiverUserId = Integer.parseInt(receiverUserId);
		this.content = content;
	}
	
	public int getSenderUserId() {
		return this.senderUserId;
	}
	
	public int getReceiverUserId() {
		return this.receiverUserId;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setSenderUserId(String senderUserId) {
		this.senderUserId = Integer.parseInt(senderUserId);
	}
	
	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = Integer.parseInt(receiverUserId);
	}
}
