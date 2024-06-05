package lld.torrent;

public class Chunk {
    private String chunkId;
    private byte[] data;
    private String fileId;

    public Chunk(String chunkId, byte[] data, String fileId) {
        this.chunkId = chunkId;
        this.data = data;
        this.fileId = fileId;
    }

    public String getChunkId() {
        return chunkId;
    }

    public byte[] getData() {
        return data;
    }

    public String getFileId() {
        return fileId;
    }
}
