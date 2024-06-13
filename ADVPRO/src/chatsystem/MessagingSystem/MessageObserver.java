package chatsystem.MessagingSystem;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // MessageObserver Interface (Observer Pattern)
public interface MessageObserver {
    void onNewMessage(Message message);
}
