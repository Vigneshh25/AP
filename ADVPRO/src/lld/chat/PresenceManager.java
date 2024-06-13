package lld.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // PresenceManager class (Singleton)
class PresenceManager {
    private static PresenceManager instance;
    private Map<User, Boolean> presenceMap;
    private List<PresenceObserver> observers;

    private PresenceManager() {
        this.presenceMap = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public static synchronized PresenceManager getInstance() {
        if (instance == null) {
            instance = new PresenceManager();
        }
        return instance;
    }

    public void setPresence(User user, boolean online) {
        presenceMap.put(user, online);
        notifyObservers(user, online);
    }

    public void addObserver(PresenceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PresenceObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(User user, boolean online) {
        for (PresenceObserver observer : observers) {
            observer.onPresenceChange(user, online);
        }
    }
}
