package ratelimiter;

public class RateLimiterDemo {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello!");
		RateLimiterManager manager = new RateLimiterManager();
		manager.addRateLimiterForUser("user1", RateLimiterType.SlidingWindow, 5, 5000);
		for(int i=0;i<3;i++) {
			manager.isRequestAllowed("user1");
		}
		Thread.sleep(4000);
		manager.isRequestAllowed("user1");
		manager.isRequestAllowed("user1");
		Thread.sleep(1000);
		for(int i=0;i<5;i++) {
			manager.isRequestAllowed("user1");
		}
		
	}

}
