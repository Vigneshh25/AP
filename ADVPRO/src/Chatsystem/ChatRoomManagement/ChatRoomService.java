package Chatsystem.ChatRoomManagement;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // ChatRoomService
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public void createChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoomById(String id) {
        return chatRoomRepository.findById(id);
    }
}
