package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */
class DefaultTokenVerifier implements TokenVerifier {
    @Override
    public boolean verifyToken(String enteredCode, String generatedCode) {
        // Implementation details...
        return enteredCode.equals(generatedCode);
    }
}
