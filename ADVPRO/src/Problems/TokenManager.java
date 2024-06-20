package Problems;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {
    private Map<String, TokenInfo> tokenMap;
    
    public TokenManager() {
        this.tokenMap = new HashMap<>();
    }
    
    /**
     * Generates a new authentication token for the given user.
     * @param userId The user ID for whom the token is generated.
     * @param timeToLive The time to live (in seconds) for the token.
     * @return The generated authentication token.
     */
    public String generateToken(int userId, long timeToLive) {
        String token = UUID.randomUUID().toString();
        long expiryTime = Instant.now().getEpochSecond() + timeToLive;
        tokenMap.put(token, new TokenInfo(userId, expiryTime));
        return token;
    }
    
    /**
     * Renews an existing authentication token, extending its expiry time.
     * @param token The token to renew.
     * @param timeToLive The new time to live (in seconds) for the token.
     * @return true if the token was successfully renewed, false if the token does not exist.
     */
    public boolean renewToken(String token, long timeToLive) {
        if (tokenMap.containsKey(token)) {
            long newExpiryTime = Instant.now().getEpochSecond() + timeToLive;
            tokenMap.get(token).setExpiryTime(newExpiryTime);
            return true;
        }
        return false;
    }
    
    /**
     * Checks if a token is valid (i.e., not expired).
     * @param token The token to check.
     * @return true if the token is valid (not expired), false otherwise.
     */
    public boolean isValidToken(String token) {
        if (tokenMap.containsKey(token)) {
            long currentEpochSecond = Instant.now().getEpochSecond();
            long tokenExpiryTime = tokenMap.get(token).getExpiryTime();
            return currentEpochSecond <= tokenExpiryTime;
        }
        return false;
    }
    
    /**
     * Returns the number of unexpired tokens currently in the system.
     * @return The count of unexpired tokens.
     */
    public int countUnexpiredTokens() {
        long currentEpochSecond = Instant.now().getEpochSecond();
        return (int) tokenMap.values().stream()
                .filter(tokenInfo -> tokenInfo.getExpiryTime() >= currentEpochSecond)
                .count();
    }
    
    /**
     * Inner class representing token information.
     */
    private static class TokenInfo {
        private int userId;
        private long expiryTime;
        
        public TokenInfo(int userId, long expiryTime) {
            this.userId = userId;
            this.expiryTime = expiryTime;
        }
        
        public int getUserId() {
            return userId;
        }
        
        public long getExpiryTime() {
            return expiryTime;
        }
        
        public void setExpiryTime(long expiryTime) {
            this.expiryTime = expiryTime;
        }
    }
    
    public static void main(String[] args) {
        TokenManager tokenManager = new TokenManager();
        
        // Generate a token with 10 seconds TTL
        String token1 = tokenManager.generateToken(1, 10);
        System.out.println("Token 1 generated: " + token1);
        
        // Wait for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check if token1 is valid
        System.out.println("Is token1 valid? " + tokenManager.isValidToken(token1));
        
        // Renew token1 with 15 seconds TTL
        tokenManager.renewToken(token1, 15);
        System.out.println("Token 1 renewed.");
        
        // Check if token1 is valid after renewal
        System.out.println("Is token1 valid after renewal? " + tokenManager.isValidToken(token1));
        
        // Wait for 10 seconds (token1 expires)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check if token1 is valid after expiry
        System.out.println("Is token1 valid after expiry? " + tokenManager.isValidToken(token1));
        
        // Count unexpired tokens
        System.out.println("Number of unexpired tokens: " + tokenManager.countUnexpiredTokens());
    }
}
