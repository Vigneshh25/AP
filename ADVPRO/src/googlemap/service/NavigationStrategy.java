package googlemap.service;

import googlemap.entity.*;

public interface NavigationStrategy {
    Route calculateRoute(Location start, Location end);
}
