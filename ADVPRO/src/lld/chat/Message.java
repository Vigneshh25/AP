package lld.chat;

import java.time.LocalDateTime;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // Message class
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
