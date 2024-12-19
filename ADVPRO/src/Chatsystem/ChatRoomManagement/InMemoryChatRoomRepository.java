package Chatsystem.ChatRoomManagement;

import java.util.HashMap;
import java.util.Map;

public class InMemoryChatRoomRepository implements ChatRoomRepository {
    private final Map<String, ChatRoom> chatRoomMap = new HashMap<>();

    @Override
    public void save(ChatRoom chatRoom) {
        chatRoomMap.put(chatRoom.getId(), chatRoom);
    }

    @Override
    public ChatRoom findById(String id) {
        return chatRoomMap.get(id);
    }
}
