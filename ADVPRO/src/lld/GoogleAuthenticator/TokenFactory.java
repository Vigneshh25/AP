package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Factory Method Pattern
interface TokenFactory {
    String generateToken(String secretKey, long currentTimeMillis);
}
