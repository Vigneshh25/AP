package googlemap;

import googlemap.entity.*;
import googlemap.service.*;

import java.util.List;

public class MapFacade {
    private MapTileService mapTileService = MapTileService.getInstance();
    private SearchService searchService = SearchService.getInstance();
    private NavigationService navigationService = NavigationService.getInstance();
    private TrafficService trafficService = TrafficService.getInstance();
    private POIService poiService = POIService.getInstance();
    private UserContentService userContentService = UserContentService.getInstance();
    private OfflineSupportService offlineSupportService = OfflineSupportService.getInstance();

    public MapTile getMapTile(String tileId) {
        return mapTileService.getTile(tileId);
    }

    public void addMapTile(String tileId, byte[] data) {
        mapTileService.addTile(tileId, data);
    }

    public List<Location> searchLocations(String query) {
        return searchService.search(query);
    }

    public Route calculateRoute(Location start, Location end) {
        return navigationService.calculateRoute(start, end);
    }

    public void updateTraffic(String locationId, String trafficInfo) {
        trafficService.updateTrafficData(locationId, trafficInfo);
    }

    public List<POI> searchPOIs(String query) {
        return poiService.searchPOIs(query);
    }

    public void addReview(String poiId, Review review) {
        userContentService.addReview(poiId, review);
    }

    public byte[] getOfflineMapArea(String areaId) {
        return offlineSupportService.getOfflineMapArea(areaId);
    }
}
