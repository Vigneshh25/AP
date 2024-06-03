package lld.GoogleAuthenticator.version;

class TokenOperationInvoker {
    private TokenCommand tokenCommand;

    public void setCommand(TokenCommand tokenCommand) {
        this.tokenCommand = tokenCommand;
    }

    public void executeOperation() {
        if (tokenCommand != null) {
            tokenCommand.execute();
        }
    }
}
