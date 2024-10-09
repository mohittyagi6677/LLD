package ratelimiter;

public class TokenBucketRateLimiter {
        
        private int capacity;
        private int tokens;
        private long lastRefillTime;
        private int refillRatePerSecond;

        private int availableCredits;
        private int maxCreditsAllowed ; // Example cap, can be adjusted
        
        public TokenBucketRateLimiter(int capacity, int refillRatePerSecond) {
            this.capacity = capacity;
            this.tokens = capacity;
            this.refillRatePerSecond = refillRatePerSecond;
            this.lastRefillTime = System.currentTimeMillis();

            this.availableCredits = 0;
            this.maxCreditsAllowed = 2 * capacity; // Example cap, can be adjusted
        }
        
        public synchronized boolean isRequestAllowed(String userId) {
            long currentTime = System.currentTimeMillis();
            int tokensToRefill = (int)((currentTime - lastRefillTime)/1000) * refillRatePerSecond;
            if(tokens+tokensToRefill > capacity) {
                this.availableCredits += tokens+tokensToRefill-capacity;
                this.availableCredits = Math.min(this.availableCredits, this.maxCreditsAllowed);
            }

            tokens = Math.min(capacity, tokens + tokensToRefill);
            lastRefillTime = currentTime;
            if(tokens > 0) {
                tokens--;
                System.out.println("Request allowed for user: "+userId);
                return true;
            } else if (availableCredits > 0) {
                availableCredits--;
                System.out.println("Request allowed for user using credits: " + userId);
                return true;
            }
            System.out.println("Request denied for user: "+userId);
            return false;
        }
    
}
