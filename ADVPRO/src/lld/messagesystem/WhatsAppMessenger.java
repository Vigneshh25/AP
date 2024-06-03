package lld.messagesystem;

import java.time.LocalDateTime;
import java.util.*;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Other user-related methods
}

// Message class
class Message {
    private String messageId;
    private User sender;
    private List<User> receivers;
    private String content;
    private LocalDateTime timestamp;
    private boolean isEncrypted;
    private boolean isDelivered;
    private boolean isRead;

    public Message(String messageId, User sender, List<User> receivers, String content) {
        this.messageId = messageId;
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isEncrypted = false;
        this.isDelivered = false;
        this.isRead = false;
    }

    // Getters and setters
    public String getMessageId() {
        return messageId;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getReceivers() {
        return receivers;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    // Other methods for marking message as delivered and read
}

// MessageManager class
class MessageManager {
    private List<Message> sentMessages;
    private List<Message> receivedMessages;

    public MessageManager() {
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        sentMessages.add(message);
        // Simulate sending message (in reality, this would involve network operations)
        System.out.println("Message sent: " + message.getContent());
    }

    public void receiveMessage(Message message) {
        receivedMessages.add(message);
        System.out.println("Message received: " + message.getContent());
        // Notify observers about new received message (if needed)
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    // Other message management methods
}

// MessageSearchStrategy interface (Strategy)
interface MessageSearchStrategy {
    List<Message> searchMessages(List<Message> messages, String keyword);
}

// KeywordSearchStrategy class (Strategy)
class KeywordSearchStrategy implements MessageSearchStrategy {
    @Override
    public List<Message> searchMessages(List<Message> messages, String keyword) {
        List<Message> result = new ArrayList<>();
        for (Message message : messages) {
            if (message.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(message);
            }
        }
        return result;
    }
}

// SenderSearchStrategy class (Strategy)
class SenderSearchStrategy implements MessageSearchStrategy {
    @Override
    public List<Message> searchMessages(List<Message> messages, String senderUsername) {
        List<Message> result = new ArrayList<>();
        for (Message message : messages) {
            if (message.getSender().getUsername().equalsIgnoreCase(senderUsername)) {
                result.add(message);
            }
        }
        return result;
    }
}

// SearchManager class
class SearchManager {
    private MessageSearchStrategy searchStrategy;

    public void setSearchStrategy(MessageSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Message> searchMessages(List<Message> messages, String keyword) {
        return searchStrategy.searchMessages(messages, keyword);
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

// MessageEncryptionDecorator class (Decorator)
class MessageEncryptionDecorator extends Message {
    private Message message;

    public MessageEncryptionDecorator(Message message) {
        super(message.getMessageId(), message.getSender(), message.getReceivers(), message.getContent());
        this.message = message;
    }

    @Override
    public String getContent() {
        return "Encrypted: " + message.getContent();
    }

    @Override
    public boolean isEncrypted() {
        return true;
    }
}

// MessageState interface (State)
interface MessageState {
    void handleMessage(Message message);
}

// ComposingState class (State)
class ComposingState implements MessageState {
    @Override
    public void handleMessage(Message message) {
        System.out.println("Message is being composed: " + message.getContent());
    }
}

// SentState class (State)
class SentState implements MessageState {
    @Override
    public void handleMessage(Message message) {
        System.out.println("Message has been sent: " + message.getContent());
        message.setDelivered(true);
    }
}

// ReceivedState class (State)
class ReceivedState implements MessageState {
    @Override
    public void handleMessage(Message message) {
        System.out.println("Message has been received: " + message.getContent());
        message.setRead(true);
    }
}

// MessageStateContext class
class MessageStateContext {
    private MessageState currentState;

    public MessageStateContext() {
        this.currentState = new ComposingState();
    }

    public void setState(MessageState state) {
        this.currentState = state;
    }

    public void handleMessage(Message message) {
        currentState.handleMessage(message);
    }
}

// Main Class
public class WhatsAppMessenger {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("user1", "john.doe", "password1");
        User user2 = new User("user2", "alice.smith", "password2");

        // Create message objects
        Message message1 = new Message("msg1", user1, Collections.singletonList(user2), "Hi Alice, how are you?");
        Message message2 = new Message("msg2", user2, Collections.singletonList(user1), "Hi John, I'm doing well.");

        // Create message manager
        MessageManager messageManager = new MessageManager();

        // Send and receive messages
        messageManager.sendMessage(message1);
        messageManager.receiveMessage(message2);

        // Search messages
        SearchManager searchManager = new SearchManager();
        searchManager.setSearchStrategy(new KeywordSearchStrategy());
        List<Message> searchResults = searchManager.searchMessages(messageManager.getReceivedMessages(), "John");
        System.out.println("Search results:");
        for (Message msg : searchResults) {
            System.out.println(msg.getContent());
        }

        // Add message encryption using decorators
        Message encryptedMessage = new MessageEncryptionDecorator(message2);
        System.out.println("Encrypted content: " + encryptedMessage.getContent());

        // Manage presence
        PresenceManager presenceManager = PresenceManager.getInstance();
        presenceManager.addObserver((user, online) -> System.out.println(user.getUsername() + " is " + (online ? "online" : "offline")));
        presenceManager.setPresence(user1, true);
        presenceManager.setPresence(user2, false);

        // Message state handling
        MessageStateContext stateContext = new MessageStateContext();
        stateContext.setState(new SentState());
        stateContext.handleMessage(message1);

        stateContext.setState(new ReceivedState());
        stateContext.handleMessage(message2);
    }
}
