package lld.chat;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // ChatServerProxy class (Proxy)
class ChatServerProxy {
    private ChatServer chatServer;

    public ChatServerProxy() {
        this.chatServer = new ChatServer();
    }

    public void sendMessage(Message message) {
        chatServer.sendMessage(message);
    }
}
