package tes.awslambda;

import java.util.ArrayList;
import java.util.List;

// Observer Pattern
interface Observer {
    void update(String event);
}

class EventSource {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public void triggerEvent(String event) {
        notifyObservers(event);
    }
}

class EventListener implements Observer {
    private String name;

    public EventListener(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println("Event Listener " + name + " received event: " + event);
    }
}

// Example usage
public class MainObserver {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        Observer listener1 = new EventListener("Listener 1");
        Observer listener2 = new EventListener("Listener 2");

        eventSource.attach(listener1);
        eventSource.attach(listener2);

        eventSource.triggerEvent("Event A");
        eventSource.triggerEvent("Event B");
    }
}
