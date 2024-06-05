package lld.torrent;

import java.util.*;

class Peer {
    private String peerId;
    private List<File> sharedFiles;
    private List<Chunk> downloadedChunks;

    public Peer(String peerId) {
        this.peerId = peerId;
        this.sharedFiles = new ArrayList<>();
        this.downloadedChunks = new ArrayList<>();
    }

    public void shareFile(File file) {
        sharedFiles.add(file);
        Tracker.getInstance().notifyFileShared(file, this);
    }

    public void downloadChunk(Chunk chunk) {
        downloadedChunks.add(chunk);
    }

    public List<File> getSharedFiles() {
        return sharedFiles;
    }

    public List<Chunk> getDownloadedChunks() {
        return downloadedChunks;
    }

    public String getPeerId() {
        return peerId;
    }
}
