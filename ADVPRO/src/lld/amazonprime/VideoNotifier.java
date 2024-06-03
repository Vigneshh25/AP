package lld.amazonprime;

import java.util.ArrayList;
import java.util.List;

class VideoNotifier {
    private List<Observer> observers;

    public VideoNotifier() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
