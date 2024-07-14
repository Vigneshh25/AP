package googlemap.service;

import java.util.HashMap;
import java.util.Map;

public class OfflineSupportService {
    private Map<String, byte[]> offlineMapData = new HashMap<>();

    private static OfflineSupportService instance;

    private OfflineSupportService() {}

    public static synchronized OfflineSupportService getInstance() {
        if (instance == null) {
            instance = new OfflineSupportService();
        }
        return instance;
    }

    public void downloadMapArea(String areaId, byte[] data) {
        offlineMapData.put(areaId, data);
    }

    public byte[] getOfflineMapArea(String areaId) {
        return offlineMapData.get(areaId);
    }
}
