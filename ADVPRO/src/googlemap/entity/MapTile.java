package googlemap.entity;

public class MapTile {
    private String tileId;
    private byte[] data;

    public MapTile(String tileId, byte[] data) {
        this.tileId = tileId;
        this.data = data;
    }

    public String getTileId() {
        return tileId;
    }

    public byte[] getData() {
        return data;
    }
}
