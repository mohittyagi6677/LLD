package sample;

import sample.Model.Message;
import sample.Model.Topic;
import sample.Service.Consumer;
import sample.Service.MessageQueue;

public class Application {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("hello world");
		MessageQueue mq = new MessageQueue();
		Topic t1 = mq.createTopic();
		Consumer cons1 = new Consumer("A");
		mq.subscribe(t1, cons1);
		Consumer cons2 = new Consumer("B");
		mq.subscribe(t1, cons2);
		
		Topic t2 = mq.createTopic();
		Consumer cons3 = new Consumer("C");
		mq.subscribe(t2, cons3);
		
		mq.publish(t1, new Message("1", "1st content"));
		mq.publish(t1, new Message("2", "2nd content"));
		Thread.sleep(15000);
		mq.publish(t2, new Message("3","3rd content"));
	}

}
