package lld;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// User class
class User {
    private String userId;
    private String username;
    private String password;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// Message class
class Message {
    private String messageId;
    private User sender;
    private String content;
    private LocalDateTime timestamp;

    public Message(String messageId, User sender, String content) {
        this.messageId = messageId;
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

// ChatRoom class
class ChatRoom {
    private String roomId;
    private List<User> participants;
    private List<Message> messages;

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Getters and setters
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }
}

// PresenceObserver interface
interface PresenceObserver {
    void onPresenceChange(User user, boolean online);
}

// PresenceManager class (Singleton)
class PresenceManager {
    private static PresenceManager instance;
    private Map<User, Boolean> presenceMap;
    private List<PresenceObserver> observers;

    private PresenceManager() {
        this.presenceMap = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public static synchronized PresenceManager getInstance() {
        if (instance == null) {
            instance = new PresenceManager();
        }
        return instance;
    }

    public void setPresence(User user, boolean online) {
        presenceMap.put(user, online);
        notifyObservers(user, online);
    }

    public void addObserver(PresenceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PresenceObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(User user, boolean online) {
        for (PresenceObserver observer : observers) {
            observer.onPresenceChange(user, online);
        }
    }
}

// ChatServerProxy class (Proxy)
class ChatServerProxy {
    private ChatServer chatServer;

    public ChatServerProxy() {
        this.chatServer = new ChatServer();
    }

    public void sendMessage(Message message) {
        chatServer.sendMessage(message);
    }
}

// ChatServer class
class ChatServer {
    private Map<String, ChatRoom> chatRooms;
    private Map<String, List<Message>> messageHistory;

    public ChatServer() {
        this.chatRooms = new HashMap<>();
        this.messageHistory = new HashMap<>();
    }

    public void sendMessage(Message message) {
        String roomId = message.getMessageId();
        ChatRoom room = chatRooms.get(roomId);
        if (room != null) {
            room.sendMessage(message);
            storeMessageInHistory(message);
        }
    }

    private void storeMessageInHistory(Message message) {
        String roomId = message.getMessageId();
        List<Message> roomMessages = messageHistory.computeIfAbsent(roomId, k -> new ArrayList<>());
        roomMessages.add(message);
    }

    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
    }

    public ChatRoom getChatRoom(String roomId) {
        return chatRooms.get(roomId);
    }
}

// LoadBalancer class
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

// Main Class
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
        PresenceObserver observer = new PresenceObserver() {
            @Override
            public void onPresenceChange(User user, boolean online) {
                System.out.println(user.getUsername() + " is " + (online ? "online" : "offline"));
            }
        };

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
