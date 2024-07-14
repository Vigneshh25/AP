package googlemap;

import googlemap.entity.*;
import googlemap.service.*;

public class Main {
    public static void main(String[] args) {
        // Initialize facade
        MapFacade mapFacade = new MapFacade();

        // Add some initial data to SearchService
        SearchService searchService = SearchService.getInstance();
        searchService.addLocation(new Location("Empire State Building", "New York, NY", 40.748817, -73.985428));
        searchService.addLocation(new Location("Statue of Liberty", "New York, NY", 40.689247, -74.044502));
        searchService.addLocation(new Location("Central Park", "New York, NY", 40.785091, -73.968285));
        searchService.addLocation(new Location("Times Square", "New York, NY", 40.758896, -73.985130));

        // Add POIs
        POIService poiService = POIService.getInstance();
        poiService.addPOI(new POI("Central Park", new Location("Central Park", "New York, NY", 40.785091, -73.968285), "Park", 4.5));
        poiService.addPOI(new POI("Times Square", new Location("Times Square", "New York, NY", 40.758896, -73.985130), "Tourist Attraction", 4.7));

        // Add Reviews
        UserContentService userContentService = UserContentService.getInstance();
        userContentService.addReview("Central Park", new Review("user123", "Great place to relax!", 5));
        userContentService.addReview("Times Square", new Review("user456", "Very crowded but iconic!", 4));

        // Search for locations
        System.out.println("Search results for 'New York':");
        for (Location location : mapFacade.searchLocations("New York")) {
            System.out.println(location.getName() + " - " + location.getAddress());
        }

        // Calculate route
        Location start = searchService.search("Empire State Building").get(0);
        Location end = searchService.search("Statue of Liberty").get(0);
        NavigationService navigationService = NavigationService.getInstance();
        navigationService.setNavigationStrategy(new DijkstraNavigationStrategy());
        Route route = mapFacade.calculateRoute(start, end);
        System.out.println("Route from " + start.getName() + " to " + end.getName() + ":");
        for (Location loc : route.getPath()) {
            System.out.println(loc.getName());
        }

        // Display POIs
        System.out.println("POIs in category 'Tourist Attraction':");
        for (POI poi : poiService.filterPOIsByCategory("Tourist Attraction")) {
            System.out.println(poi.getName() + " - Rating: " + poi.getRating());
        }

        // Display reviews
        System.out.println("Reviews for 'Central Park':");
        for (Review review : userContentService.getReviews("Central Park")) {
            System.out.println(review.getUserId() + ": " + review.getContent() + " - Rating: " + review.getRating());
        }
    }
}

