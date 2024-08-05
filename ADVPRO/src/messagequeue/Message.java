package messagequeue;

public class Message {
    private final String payload;
    private final long timestamp;

    public Message(String payload) {
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public String getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
