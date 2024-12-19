package Chatsystem.MessagingSystem;

import Chatsystem.ChatRoomManagement.ChatRoom;
import Chatsystem.UserManagement.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private String id;
    private String content;
    private User sender;
    private String status; // sent, delivered, read
    private ChatRoom chatRoom;
    private LocalDateTime timestamp;
    private String multimediaUrl;
    private String messageType; // text, image, video, document, etc.

    public Message() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and setters...


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMultimediaUrl() {
        return multimediaUrl;
    }

    public void setMultimediaUrl(String multimediaUrl) {
        this.multimediaUrl = multimediaUrl;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
