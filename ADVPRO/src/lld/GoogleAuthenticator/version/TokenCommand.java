package lld.GoogleAuthenticator.version;

interface TokenCommand {
    void execute();
}

class GenerateTokenCommand implements TokenCommand {
    private final TokenFactory tokenFactory;
    private final String secretKey;
    private final long currentTimeMillis;

    public GenerateTokenCommand(TokenFactory tokenFactory, String secretKey, long currentTimeMillis) {
        this.tokenFactory = tokenFactory;
        this.secretKey = secretKey;
        this.currentTimeMillis = currentTimeMillis;
    }

    @Override
    public void execute() {
        String generatedToken = tokenFactory.generateToken(secretKey, currentTimeMillis);
        System.out.println("Generated Token: " + generatedToken);
    }
}

class VerifyTokenCommand implements TokenCommand {
    private final TokenVerifier tokenVerifier;
    private final String enteredCode;
    private final String generatedCode;

    public VerifyTokenCommand(TokenVerifier tokenVerifier, String enteredCode, String generatedCode) {
        this.tokenVerifier = tokenVerifier;
        this.enteredCode = enteredCode;
        this.generatedCode = generatedCode;
    }

    @Override
    public void execute() {
        boolean isVerified = tokenVerifier.verifyToken(enteredCode, generatedCode);
        System.out.println("Token Verification Result: " + isVerified);
    }
}
