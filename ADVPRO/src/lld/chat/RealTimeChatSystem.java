package lld.chat;

// WebCrawlerWithSameHostnameMain Class
public class RealTimeChatSystem {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("user1", "John", "password1");
        User user2 = new User("user2", "Alice", "password2");

        // Create chat rooms
        ChatRoom room1 = new ChatRoom("room1");
        room1.addParticipant(user1);
        room1.addParticipant(user2);

        // Create messages
        Message message1 = new Message("message1", user1, "Hello, everyone!");
        Message message2 = new Message("message2", user2, "Hi, John!");

        // Create chat server and add chat room
        ChatServer chatServer = new ChatServer();
        chatServer.addChatRoom(room1);

        // Send messages via proxy
        ChatServerProxy serverProxy = new ChatServerProxy();
        serverProxy.sendMessage(message1);
        serverProxy.sendMessage(message2);

        // Create presence manager
        PresenceManager presenceManager = PresenceManager.getInstance();
        presenceManager.setPresence(user1, true);
        presenceManager.setPresence(user2, true);

        // Create presence observer
        PresenceObserver observer = (user, online) -> System.out.println(user.getUsername() + " is " + (online ? "online" : "offline"));

        // Subscribe presence observer to presence manager
        presenceManager.addObserver(observer);

        // Create load balancer and add chat servers
        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.addServer(chatServer);
        ChatServer anotherServer = new ChatServer();
        loadBalancer.addServer(anotherServer);

        // Get chat server from load balancer and send messages
        ChatServer balancedServer = loadBalancer.getChatServer();
        if (balancedServer != null) {
            balancedServer.sendMessage(message1);
            balancedServer.sendMessage(message2);
        }
    }
}
