package googlemap.service;

import googlemap.entity.TrafficObserver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrafficService {
    private Map<String, String> trafficData = new HashMap<>();
    private Set<TrafficObserver> observers = new HashSet<>();

    private static TrafficService instance;

    private TrafficService() {}

    public static synchronized TrafficService getInstance() {
        if (instance == null) {
            instance = new TrafficService();
        }
        return instance;
    }

    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }

    public void updateTrafficData(String locationId, String trafficInfo) {
        trafficData.put(locationId, trafficInfo);
        notifyObservers(locationId, trafficInfo);
    }

    public String getTrafficData(String locationId) {
        return trafficData.get(locationId);
    }

    private void notifyObservers(String locationId, String trafficInfo) {
        for (TrafficObserver observer : observers) {
            observer.updateTraffic(locationId, trafficInfo);
        }
    }
}
