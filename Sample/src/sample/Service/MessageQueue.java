package sample.Service;

import java.util.UUID;

import sample.Model.Message;
import sample.Model.Topic;

public class MessageQueue {
	
	public MessageQueue() {
		
	}
	
	public Topic createTopic() {
		Topic topic = new Topic(UUID.randomUUID().toString());
		return topic;
	}
	
	public void subscribe(Topic topic,Consumer consumer) {
		topic.addConsumer(consumer);
		consumer.setTopic(topic);
	}
	
	public void publish(Topic topic,Message message) {
		topic.addMessage(message);
		new Thread(()->topic.publish()).start();
	}
	

}
