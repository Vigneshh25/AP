package lld.GoogleAuthenticator.version;

class GoogleAuthenticator {
    private static GoogleAuthenticator instance;
    private final TokenFactory tokenFactory;
    private final TokenVerifier tokenVerifier;
    private final UserDao userDao;
    private final TokenOperationInvoker tokenOperationInvoker;

    private GoogleAuthenticator() {
        this.tokenFactory = new DefaultTokenFactory();
        this.tokenVerifier = new DefaultTokenVerifier();
        this.userDao = new InMemoryUserDao();
        this.tokenOperationInvoker = new TokenOperationInvoker();
    }

    public static synchronized GoogleAuthenticator getInstance() {
        if (instance == null) {
            instance = new GoogleAuthenticator();
        }
        return instance;
    }

    public void generateAndExecuteTokenGenerationCommand(String userId, long currentTimeMillis) {
        User user = userDao.getUser(userId);
        if (user != null) {
            String secretKey = user.getSecretKey();
            TokenCommand generateTokenCommand = new GenerateTokenCommand(tokenFactory, secretKey, currentTimeMillis);
            tokenOperationInvoker.setCommand(generateTokenCommand);
            tokenOperationInvoker.executeOperation();
        } else {
            System.out.println("User not found!");
        }
    }

    public void generateAndExecuteTokenVerificationCommand(String userId, String enteredCode, String generatedCode) {
        User user = userDao.getUser(userId);
        if (user != null) {
            TokenCommand verifyTokenCommand = new VerifyTokenCommand(tokenVerifier, enteredCode, generatedCode);
            tokenOperationInvoker.setCommand(verifyTokenCommand);
            tokenOperationInvoker.executeOperation();
        } else {
            System.out.println("User not found!");
        }
    }
}
