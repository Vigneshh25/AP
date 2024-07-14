package googlemap.service;

import googlemap.entity.MapTile;

import java.util.HashMap;
import java.util.Map;

public class MapTileService {
    private Map<String, MapTile> tileCache = new HashMap<>();

    private static MapTileService instance;

    private MapTileService() {}

    public static synchronized MapTileService getInstance() {
        if (instance == null) {
            instance = new MapTileService();
        }
        return instance;
    }

    public MapTile getTile(String tileId) {
        return tileCache.get(tileId);
    }

    public void addTile(String tileId, byte[] data) {
        tileCache.put(tileId, new MapTile(tileId, data));
    }
}
