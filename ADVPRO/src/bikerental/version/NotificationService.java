package bikerental.version;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class NotificationService {
    private List<Observer> observers;

    public NotificationService() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
