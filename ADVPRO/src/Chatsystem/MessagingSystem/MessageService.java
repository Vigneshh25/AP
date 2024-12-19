package Chatsystem.MessagingSystem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // MessageService
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getChatHistory(String chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId);
    }

    public List<Message> searchMessages(String chatRoomId, String keyword) {
        List<Message> chatHistory = messageRepository.findByChatRoomId(chatRoomId);
        return chatHistory.stream()
                .filter(message -> message.getContent().contains(keyword) || message.getSender().getUsername().contains(keyword))
                .collect(Collectors.toList());
    }
}
