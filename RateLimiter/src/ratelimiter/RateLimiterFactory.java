package ratelimiter;

public class RateLimiterFactory {
	
	public IRateLimiter createRateLimiter(RateLimiterType type,int maxRequestsAllowed,long windowSize) {
		switch (type) {
		case RateLimiterType.SlidingWindow: {
			return new SlidingWindowRateLimiter(maxRequestsAllowed, windowSize);	
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
	}

}
