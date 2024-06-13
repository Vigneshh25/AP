package chatsystem.MessagingSystem;

import java.util.List;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // MessageRepository Interface
public interface MessageRepository {
    void save(Message message);

    List<Message> findByChatRoomId(String chatRoomId);
}
