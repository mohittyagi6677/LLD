package sample.Model;

public class Message {
	private String id;
	private String content;
	
	public Message(String id,String content) {
		this.id=id;
		this.content=content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}


}
