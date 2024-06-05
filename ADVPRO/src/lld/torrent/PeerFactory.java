package lld.torrent;

class PeerFactory {
    public static Peer createPeer(String peerId) {
        return new Peer(peerId);
    }
}

class FileFactory {
    public static File createFile(String fileId) {
        return new File(fileId);
    }
}

class ChunkFactory {
    public static Chunk createChunk(String chunkId, byte[] data, String fileId) {
        return new Chunk(chunkId, data, fileId);
    }
}
