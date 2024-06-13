package chatsystem.ChatRoomManagement;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // ChatRoomRepository Interface
public interface ChatRoomRepository {
    void save(ChatRoom chatRoom);

    ChatRoom findById(String id);
}
