package webcrawler;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String url);
}

class URLObserver implements Observer {
    @Override
    public void update(String url) {
        System.out.println("New URL added: " + url);
    }
}

class URLNotifier {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String url) {
        for (Observer observer : observers) {
            observer.update(url);
        }
    }
}
