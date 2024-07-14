package googlemap.service;

import googlemap.entity.Location;
import googlemap.entity.Route;

import java.util.ArrayList;
import java.util.List;

public class DijkstraNavigationStrategy implements NavigationStrategy {
    @Override
    public Route calculateRoute(Location start, Location end) {
        // Implement Dijkstra's algorithm
        List<Location> path = new ArrayList<>();
        double distance = 0.0;
        double duration = 0.0;

        // Dummy implementation
        path.add(start);
        path.add(end);
        distance = 10.0;
        duration = 15.0;

        return new Route(path, distance, duration);
    }
}
