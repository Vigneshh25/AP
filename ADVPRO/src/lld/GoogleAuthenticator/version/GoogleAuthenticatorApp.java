package lld.GoogleAuthenticator.version;

public class GoogleAuthenticatorApp {
    public static void main(String[] args) {
        GoogleAuthenticator authenticator = GoogleAuthenticator.getInstance();

        User user = new User("123", "John Doe", "secretKey123");
        authenticator.generateAndExecuteTokenGenerationCommand(user.getUserId(), System.currentTimeMillis());

        // Simulating user entering the code
        String enteredCode = "secretKey123"; // Replace with the actual user input.txt
        authenticator.generateAndExecuteTokenVerificationCommand(user.getUserId(), enteredCode, "generated_token");
    }
}
