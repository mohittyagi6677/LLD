package sample.Model;

import java.util.ArrayList;
import java.util.List;

import sample.Service.Consumer;

public class Topic {
	String id;
	List<Consumer> consumers;
	List<Message> messages;
	public Topic(String id) {
		this.id=id;
		consumers = new ArrayList<>();
		messages=new ArrayList<>();
	}
	
	public void addConsumer(Consumer consumer) {
		consumers.add(consumer);
	}
	
	public void addMessage(Message message) {
		messages.add(message);	
	}
	
	public List<Message> getMessages() {
		return this.messages;
	}
	
	public void publish() {
		for(Consumer cons: consumers) {
			new Thread(() -> {
				try {
					cons.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
	}
	
	
	
}
