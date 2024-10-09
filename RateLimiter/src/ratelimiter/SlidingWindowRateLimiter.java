package ratelimiter;
import java.util.*;

public class SlidingWindowRateLimiter implements IRateLimiter{
	Queue<Long> timeStamps ;
	int maxRequestsAllowed;
	long windowSize;
	
	int availableCredits;
	int maxCreditsAllowed;
	long lastResetTime;
	
	public SlidingWindowRateLimiter(int maxRequestsAllowed,long windowSize) {
		timeStamps=new LinkedList<Long>();
		this.maxRequestsAllowed=maxRequestsAllowed;
		this.windowSize=windowSize;
		
		
		this.lastResetTime=System.currentTimeMillis();
		this.availableCredits=0;
		this.maxCreditsAllowed=2*maxRequestsAllowed;
	}
	

	@Override
	public synchronized boolean isRequestAllowed(String userId) {
		Long now = System.currentTimeMillis();
		
		
		long timeElapsed=now-this.lastResetTime;
		if(timeElapsed>windowSize) {
			int totalWindows = (int) (timeElapsed/windowSize);
			int totalRequestAllowedWithBuffer = (totalWindows*maxRequestsAllowed);
			int newCredits = totalRequestAllowedWithBuffer-timeStamps.size();
			this.availableCredits+=newCredits;
			this.availableCredits=Math.min(this.availableCredits, this.maxCreditsAllowed);
			this.lastResetTime=now;
			
			
		}
		while(!timeStamps.isEmpty() && now-timeStamps.peek()>=windowSize) {
			timeStamps.poll();
		}
		if(timeStamps.size()<maxRequestsAllowed) {
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

}
