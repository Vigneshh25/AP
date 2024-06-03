package lld.GoogleAuthenticator.version;

interface TokenVerifier {
    boolean verifyToken(String enteredCode, String generatedCode);
}

class DefaultTokenVerifier implements TokenVerifier {
    @Override
    public boolean verifyToken(String enteredCode, String generatedCode) {
        // Simple equality check for token verification
        return enteredCode.equals(generatedCode);
    }
}
