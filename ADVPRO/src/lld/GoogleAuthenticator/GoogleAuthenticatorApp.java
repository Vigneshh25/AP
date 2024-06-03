package lld.GoogleAuthenticator;


public class GoogleAuthenticatorApp {
    public static void main(String[] args) {
        GoogleAuthenticator authenticator = GoogleAuthenticator.getInstance();

        User user = new User("123", "John Doe", "secretKey123");
        authenticator.generateAndExecuteTokenGenerationCommand(user.getUserId(), System.currentTimeMillis());

        // Simulating user entering the code
        String enteredCode = "generated_token"; // Replace with the actual user input
        authenticator.generateAndExecuteTokenVerificationCommand(user.getUserId(), enteredCode, "generated_token");
    }
}
