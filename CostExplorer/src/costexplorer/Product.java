package costexplorer;

public class Product {
	String id;
	String name;
	Subscription subscription;
	
	public Product(String id, String name, Subscription subscription) {
		this.id = id;
		this.name = name;
		this.subscription = subscription;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	
	

}
