package ratelimiter;

import java.util.*;

enum Config{
	capacity,
	refillRatePerSecond,
	windowSizeInSeconds
}
public class RateLimiterFactory {
	
	public IRateLimiter createRateLimiter(RateLimiterType type,Map<Config,Object> config) {
		switch (type) {
		case SlidingWindow: {
			return new SlidingWindowRateLimiter((int)config.get(Config.capacity), (int)(config.get(Config.windowSizeInSeconds)));	
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
	}

}
