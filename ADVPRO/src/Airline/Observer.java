package Airline;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class Customer implements Observer {
    private String email;

    public Customer(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + email + ": " + message);
    }
}

class NotificationService {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
