package sample.Service;

import sample.Model.Message;
import sample.Model.Topic;

public class Consumer {
	int offset;
	String name;
	Topic topic;
	private static final int sleepTime=10000;
	
	public Consumer(String name) {
		this.name=name;
	}
	
	public void setTopic(Topic topic) {
		this.topic=topic;
	}
	
	public void consume() throws InterruptedException {
		synchronized (this.name) {
			while(topic.getMessages().size()>this.offset) {
				Message message = topic.getMessages().get(offset);
				System.out.println("Consumer "+this.name+" started consuming message with id: "+message.getId());
				Thread.sleep(sleepTime);
				System.out.println("Consumer "+this.name+" done consuming message with id: "+message.getId());
				this.offset++;
			}
		}
		
	}

}
