package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Strategy Pattern
interface TokenVerifier {
    boolean verifyToken(String enteredCode, String generatedCode);
}
