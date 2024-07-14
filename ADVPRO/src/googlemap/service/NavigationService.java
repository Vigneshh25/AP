package googlemap.service;

import googlemap.entity.*;

public class NavigationService {
    private NavigationStrategy navigationStrategy;

    private static NavigationService instance;

    private NavigationService() {}

    public static synchronized NavigationService getInstance() {
        if (instance == null) {
            instance = new NavigationService();
        }
        return instance;
    }

    public void setNavigationStrategy(NavigationStrategy navigationStrategy) {
        this.navigationStrategy = navigationStrategy;
    }

    public Route calculateRoute(Location start, Location end) {
        return navigationStrategy.calculateRoute(start, end);
    }
}
