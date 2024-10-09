package ratelimiter;

public interface IRateLimiter {
	
	boolean isRequestAllowed(String userId);

}
