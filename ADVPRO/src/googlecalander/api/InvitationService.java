package googlecalander.api;

import googlecalander.model.Event;
import googlecalander.model.InvitationObserver;
import googlecalander.model.User;

import java.util.ArrayList;
import java.util.List;

public class InvitationService {
    private List<InvitationObserver> observers = new ArrayList<>();

    public void addObserver(InvitationObserver observer) {
        observers.add(observer);
    }

    public void invite(User user, Event event) {
        // Send invitation (e.g., email/SMS)
        notifyObservers(user, event);
    }

    private void notifyObservers(User user, Event event) {
        for (InvitationObserver observer : observers) {
            observer.update(user, event);
        }
    }
}
