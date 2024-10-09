package ratelimiter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

enum RateLimiterType{
	SlidingWindow,
	FixedCounter
}
public class RateLimiterManager {
	private IRateLimiter defaultRateLimiter ;
	private Map<String,IRateLimiter> rateLimiterMap;
	private RateLimiterFactory rateLimiterFactory;
	
	public RateLimiterManager() {
		rateLimiterMap = new ConcurrentHashMap<>();
		rateLimiterFactory = new RateLimiterFactory();
		defaultRateLimiter = rateLimiterFactory.createRateLimiter(RateLimiterType.SlidingWindow, 2, 5000);
	}
	
	public void addRateLimiterForUser(String userId,RateLimiterType type,int maxRequestsAllowed,long windowSize) {
		rateLimiterMap.putIfAbsent(userId, rateLimiterFactory.createRateLimiter(type,maxRequestsAllowed,windowSize));
	}
	
	public boolean isRequestAllowed(String userId) {
		return rateLimiterMap.getOrDefault(userId, defaultRateLimiter).isRequestAllowed(userId);
	}

}
