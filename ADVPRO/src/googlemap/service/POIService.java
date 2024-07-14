package googlemap.service;

import googlemap.entity.POI;

import java.util.ArrayList;
import java.util.List;

public class POIService {
    private List<POI> pois = new ArrayList<>();

    private static POIService instance;

    private POIService() {}

    public static synchronized POIService getInstance() {
        if (instance == null) {
            instance = new POIService();
        }
        return instance;
    }

    public void addPOI(POI poi) {
        pois.add(poi);
    }

    public List<POI> searchPOIs(String query) {
        List<POI> results = new ArrayList<>();
        for (POI poi : pois) {
            if (poi.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(poi);
            }
        }
        return results;
    }

    public List<POI> filterPOIsByCategory(String category) {
        List<POI> results = new ArrayList<>();
        for (POI poi : pois) {
            if (poi.getCategory().equalsIgnoreCase(category)) {
                results.add(poi);
            }
        }
        return results;
    }
}
