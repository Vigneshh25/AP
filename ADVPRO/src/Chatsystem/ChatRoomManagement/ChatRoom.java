package Chatsystem.ChatRoomManagement;

import Chatsystem.UserManagement.User;

import java.util.List;
import java.util.UUID;

public class ChatRoom {
    private String id;
    private String name;
    private List<User> participants;
    private boolean isGroupChat;

    public ChatRoom() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and setters...


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setGroupChat(boolean groupChat) {
        isGroupChat = groupChat;
    }
}
