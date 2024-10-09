package ratelimiter;
import java.util.*;

public class SlidingWindowRateLimiter implements IRateLimiter{
	Queue<Long> timeStamps ;
	int capacity;
	int windowSizeInSeconds;
	// follow up question with credits
	int availableCredits;
	int maxCreditsAllowed;
	long lastResetTime;
	
	public SlidingWindowRateLimiter(int maxRequestsAllowed,int windowSizeInSeconds) {
		timeStamps=new LinkedList<Long>();
		this.capacity=maxRequestsAllowed;
		this.windowSizeInSeconds=windowSizeInSeconds;
		
		// follow up solution to add credits as per unutilized rate limit
		this.lastResetTime=System.currentTimeMillis();
		this.availableCredits=0;
		this.maxCreditsAllowed=2*maxRequestsAllowed;
	}
	

	@Override
	public synchronized boolean isRequestAllowed(String userId) {
		Long now = System.currentTimeMillis();
		
		
		long timeElapsed=now-this.lastResetTime;
		if(timeElapsed>getWindowSizeInMilliSeconds()) {
			int totalWindows = (int) (timeElapsed/getWindowSizeInMilliSeconds());
			int totalRequestAllowedWithBuffer = (totalWindows*capacity);
			int newCredits = totalRequestAllowedWithBuffer-timeStamps.size();
			this.availableCredits+=newCredits;
			this.availableCredits=Math.min(this.availableCredits, this.maxCreditsAllowed);
			this.lastResetTime=now;	
		}
		while(!timeStamps.isEmpty() && now-timeStamps.peek()>=getWindowSizeInMilliSeconds()) {
			timeStamps.poll();
		}
		if(timeStamps.size()<capacity) {
			timeStamps.add(now);
			System.out.println("Allowing the request for user:"+userId);
			return true;
		} else if(this.availableCredits>0) {
			// use credits
			timeStamps.add(now);
			this.availableCredits--;
			return true;
		}
		System.out.println("Rejecting the request for user:"+userId);
		return false;
	}

	private long getWindowSizeInMilliSeconds() {
		return (long)windowSizeInSeconds*1000;
	}

}
