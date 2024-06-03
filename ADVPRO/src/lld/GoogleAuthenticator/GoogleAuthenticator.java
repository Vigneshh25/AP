package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Singleton Pattern
class GoogleAuthenticator {
    private static GoogleAuthenticator instance;
    private TokenFactory tokenFactory;
    private TokenVerifier tokenVerifier;
    private UserDao userDao;
    private TokenOperationInvoker tokenOperationInvoker;

    private GoogleAuthenticator() {
        this.tokenFactory = new DefaultTokenFactory();
        this.tokenVerifier = new DefaultTokenVerifier();
        this.userDao = new InMemoryUserDao();
        this.tokenOperationInvoker = new TokenOperationInvoker();
    }

    public static GoogleAuthenticator getInstance() {
        if (instance == null) {
            instance = new GoogleAuthenticator();
        }
        return instance;
    }

    // Other methods for QR code generation, user registration, etc.

    public void generateAndExecuteTokenGenerationCommand(String userId, long currentTimeMillis) {
        User user = userDao.getUser(userId);
        if (user != null) {
            String secretKey = user.getSecretKey();
            // Create the token generation command
            TokenCommand generateTokenCommand = new GenerateTokenCommand(tokenFactory, secretKey, currentTimeMillis);
            // Set the command
            tokenOperationInvoker.setCommand(generateTokenCommand);
            // Execute the command
            tokenOperationInvoker.executeOperation();
        }
    }

    public void generateAndExecuteTokenVerificationCommand(String userId, String enteredCode, String generatedCode) {
        User user = userDao.getUser(userId);
        if (user != null) {
            // Create the token verification command
            TokenCommand verifyTokenCommand = new VerifyTokenCommand(tokenVerifier, enteredCode, generatedCode);
            // Set the command
            tokenOperationInvoker.setCommand(verifyTokenCommand);
            // Execute the command
            tokenOperationInvoker.executeOperation();
        }
    }

    // Other methods...
}
