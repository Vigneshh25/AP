package Ecommerce.version;

// StockSubject.java
import java.util.ArrayList;
import java.util.List;
// Observer.java
public interface Observer {
    void update(String message);
}

// StockObserver.java
class StockObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Stock Update: " + message);
    }
}

// Subject.java
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}



class StockSubject implements Subject {
    private List<Observer> observers;
    private String stockStatus;

    public StockSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockStatus);
        }
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
        notifyObservers();
    }
}
