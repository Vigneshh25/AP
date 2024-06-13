package lld.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // LoadBalancer class
class LoadBalancer {
    private List<ChatServer> chatServers;

    public LoadBalancer() {
        this.chatServers = new ArrayList<>();
    }

    public void addServer(ChatServer server) {
        chatServers.add(server);
    }

    public ChatServer getChatServer() {
        // Implementing a simple round-robin load balancing algorithm
        if (chatServers.isEmpty()) {
            return null;
        }
        // In this example, we'll just return the first server
        // For a real implementation, you'd track and return the server with the least load
        return chatServers.get(0);
    }
}
