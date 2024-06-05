package lld.torrent;

import java.util.*;

class Tracker {
    private static Tracker instance;
    private Map<String, List<Peer>> filePeersMap;

    private Tracker() {
        filePeersMap = new HashMap<>();
    }

    public static synchronized Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    public void notifyFileShared(File file, Peer peer) {
        filePeersMap.computeIfAbsent(file.getFileId(), k -> new ArrayList<>()).add(peer);
    }

    public List<Peer> getPeersSharingFile(String fileId) {
        return filePeersMap.getOrDefault(fileId, new ArrayList<>());
    }
}
