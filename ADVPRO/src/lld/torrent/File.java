package lld.torrent;

import java.util.*;

class File {
    private String fileId;
    private List<Chunk> chunks;

    public File(String fileId) {
        this.fileId = fileId;
        this.chunks = new ArrayList<>();
    }

    public void addChunk(Chunk chunk) {
        chunks.add(chunk);
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public String getFileId() {
        return fileId;
    }
}
