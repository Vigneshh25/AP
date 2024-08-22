package BookStore.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Book> books = new ArrayList<>();
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;

    public void addBook(Book book) {
        books.add(book);
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        notifyAllObservers();
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }

    private void notifyAllObservers() {
        for (OrderObserver observer : observers) {
            observer.update();
        }
    }
}
