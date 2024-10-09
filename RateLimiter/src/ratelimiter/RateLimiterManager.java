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
	private final Map<Config,Object> defaultConfig = new HashMap<Config,Object>();
	
	public RateLimiterManager() {
		rateLimiterMap = new ConcurrentHashMap<>();
		rateLimiterFactory = new RateLimiterFactory();
		defaultConfig.put(Config.capacity, 5);
		defaultConfig.put(Config.windowSizeInSeconds, 5000);
		defaultRateLimiter = rateLimiterFactory.createRateLimiter(RateLimiterType.SlidingWindow, defaultConfig);
	}
	
	public void addRateLimiterForUser(String userId,RateLimiterType type,int maxRequestsAllowed,int windowSize) {
		Map<Config,Object> config = new HashMap<Config,Object>();
		config.put(Config.capacity, maxRequestsAllowed);
		config.put(Config.windowSizeInSeconds, windowSize);
		rateLimiterMap.putIfAbsent(userId, rateLimiterFactory.createRateLimiter(type,config));
	}
	
	public boolean isRequestAllowed(String userId) {
		return rateLimiterMap.getOrDefault(userId, defaultRateLimiter).isRequestAllowed(userId);
	}

}
