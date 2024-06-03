package lld.GoogleAuthenticator.version;

import java.util.Base64;

interface TokenFactory {
    String generateToken(String secretKey, long currentTimeMillis);
}

class DefaultTokenFactory implements TokenFactory {
    @Override
    public String generateToken(String secretKey, long currentTimeMillis) {
        // Example token generation logic using base64 encoding
        String tokenData = secretKey + ":" + currentTimeMillis;
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }
}
