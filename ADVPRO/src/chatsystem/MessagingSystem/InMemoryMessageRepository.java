package chatsystem.MessagingSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMessageRepository implements MessageRepository {
    private final Map<String, List<Message>> messageMap = new HashMap<>();

    @Override
    public void save(Message message) {
        messageMap.computeIfAbsent(message.getChatRoom().getId(), k -> new ArrayList<>()).add(message);
    }

    @Override
    public List<Message> findByChatRoomId(String chatRoomId) {
        return messageMap.getOrDefault(chatRoomId, new ArrayList<>());
    }
}
