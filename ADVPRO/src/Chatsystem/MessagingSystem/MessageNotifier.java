package Chatsystem.MessagingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // MessageNotifier
public class MessageNotifier {
    private List<MessageObserver> observers = new ArrayList<>();

    public void addObserver(MessageObserver observer) {
        observers.add(observer);
    }

    public void notifyNewMessage(Message message) {
        for (MessageObserver observer : observers) {
            observer.onNewMessage(message);
        }
    }
}
