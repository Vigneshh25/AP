package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Token Operation Invoker
class TokenOperationInvoker {
    private TokenCommand tokenCommand;

    public void setCommand(TokenCommand tokenCommand) {
        this.tokenCommand = tokenCommand;
    }

    public void executeOperation() {
        // Execute the assigned command
        tokenCommand.execute();
    }
}
