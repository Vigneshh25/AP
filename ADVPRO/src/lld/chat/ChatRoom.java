package lld.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // ChatRoom class
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
