package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Token Verification Command
class VerifyTokenCommand implements TokenCommand {
    private TokenVerifier tokenVerifier;
    private String enteredCode;
    private String generatedCode;

    public VerifyTokenCommand(TokenVerifier tokenVerifier, String enteredCode, String generatedCode) {
        this.tokenVerifier = tokenVerifier;
        this.enteredCode = enteredCode;
        this.generatedCode = generatedCode;
    }

    public void execute() {
        // Execute token verification
        boolean isVerified = tokenVerifier.verifyToken(enteredCode, generatedCode);
        System.out.println("Token Verification Result: " + isVerified);
        // Additional logic, if needed
    }
}
