package googlemap.service;

import googlemap.entity.Location;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private List<Location> locations = new ArrayList<>();

    private static SearchService instance;

    private SearchService() {}

    public static synchronized SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public List<Location> search(String query) {
        List<Location> results = new ArrayList<>();
        for (Location location : locations) {
            if (location.getName().toLowerCase().contains(query.toLowerCase()) ||
                location.getAddress().toLowerCase().contains(query.toLowerCase())) {
                results.add(location);
            }
        }
        return results;
    }

    public List<String> getAutocompleteSuggestions(String query) {
        List<String> suggestions = new ArrayList<>();
        for (Location location : locations) {
            if (location.getName().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(location.getName());
            }
        }
        return suggestions;
    }
}
