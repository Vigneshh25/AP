package meetingroom;

import java.util.*;

public class MeetingRoom {
    private String roomId;
    private List<Observer> observers;

    public MeetingRoom(String roomId) {
        this.roomId = roomId;
        this.observers = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
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
