package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */
class DefaultTokenFactory implements TokenFactory {
    @Override
    public String generateToken(String secretKey, long currentTimeMillis) {
        // Implementation details...
        return "generated_token";
    }
}
