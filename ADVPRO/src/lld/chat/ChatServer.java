package lld.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // ChatServer class
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
