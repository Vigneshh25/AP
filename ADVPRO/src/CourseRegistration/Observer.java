package CourseRegistration;

import java.util.List;

interface Observer {
    void update(String message);
}

interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
