package chatsystem;

import chatsystem.Authentication.AuthService;
import chatsystem.Authentication.SimpleAuthStrategy;
import chatsystem.ChatRoomManagement.ChatRoom;
import chatsystem.ChatRoomManagement.ChatRoomRepository;
import chatsystem.ChatRoomManagement.ChatRoomService;
import chatsystem.ChatRoomManagement.InMemoryChatRoomRepository;
import chatsystem.MessagingSystem.*;
import chatsystem.UserManagement.InMemoryUserRepository;
import chatsystem.UserManagement.User;
import chatsystem.UserManagement.UserRepository;
import chatsystem.UserManagement.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RealTimeChatSystem {
    public static void main(String[] args) {
        // User management
        UserRepository userRepository = new InMemoryUserRepository();
        UserService userService = new UserService(userRepository);

        // Authentication
        AuthService authService = new AuthService();
        authService.setAuthStrategy(new SimpleAuthStrategy(userService));

        // Chat room management
        ChatRoomRepository chatRoomRepository = new InMemoryChatRoomRepository();
        ChatRoomService chatRoomService = new ChatRoomService(chatRoomRepository);

        // Messaging system
        MessageRepository messageRepository = new InMemoryMessageRepository();
        MessageService messageService = new MessageService(messageRepository);
        MessageNotifier messageNotifier = new MessageNotifier();

        // Adding observers to print each message
        messageNotifier.addObserver(message -> System.out.println("[" + message.getTimestamp() + "] " + message.getSender().getUsername() + ": " + message.getContent()));

        // Sample user registration
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john_doe@example.com");
        user1.setPassword("password123");
        userService.registerUser(user1);

        User user2 = new User();
        user2.setUsername("jane_doe");
        user2.setEmail("jane_doe@example.com");
        user2.setPassword("password123");
        userService.registerUser(user2);

        // Adding contacts
        userService.addContact("john_doe", user2);
        userService.addContact("jane_doe", user1);

        // Sample authentication
        boolean isAuthenticated1 = authService.authenticate("john_doe", "password123");
        boolean isAuthenticated2 = authService.authenticate("jane_doe", "password123");
        System.out.println("Authentication success for john_doe: " + isAuthenticated1);
        System.out.println("Authentication success for jane_doe: " + isAuthenticated2);

        // Sample chat room creation
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName("General");
        chatRoom.setGroupChat(true);
        chatRoomService.createChatRoom(chatRoom);

        // Adding participants to the chat room
        List<User> participants = new ArrayList<>();
        participants.add(user1);
        participants.add(user2);
        chatRoom.setParticipants(participants);

        // Sample message sending without encryption
        sendMessage(messageService, messageNotifier, user1, chatRoom, "Hello, everyone!");
        sendMessage(messageService, messageNotifier, user2, chatRoom, "Hi John! How are you?");
        sendMessage(messageService, messageNotifier, user1, chatRoom, "I'm good, thanks! How about you?");

        // Display chat history
        displayChatHistory(messageService, chatRoom.getId());

        // Search messages
        List<Message> searchResults = messageService.searchMessages(chatRoom.getId(), "good");
        System.out.println("Search results for 'good':");
        for (Message message : searchResults) {
            System.out.println("[" + message.getTimestamp() + "] " + message.getSender().getUsername() + ": " + message.getContent());
        }
    }

    private static void sendMessage(MessageService messageService, MessageNotifier messageNotifier, User sender, ChatRoom chatRoom, String content) {
        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setChatRoom(chatRoom);
        message.setTimestamp(LocalDateTime.now());
        message.setStatus("sent");
        messageService.sendMessage(message);
        messageNotifier.notifyNewMessage(message);
    }

    private static void displayChatHistory(MessageService messageService, String chatRoomId) {
        List<Message> chatHistory = messageService.getChatHistory(chatRoomId);
        System.out.println("Chat History:");
        for (Message message : chatHistory) {
            System.out.println("[" + message.getTimestamp() + "] " + message.getSender().getUsername() + ": " + message.getContent());
        }
    }
}

