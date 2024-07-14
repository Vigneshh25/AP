package googlemap.entity;

public interface TrafficObserver {
    void updateTraffic(String locationId, String trafficInfo);
}
