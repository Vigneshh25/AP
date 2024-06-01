package Design_Datastructure.ratelimter.combined;

import java.time.Instant;

public class Request {
    private final Instant timestamp;
    private final Integer count;

    public Request(Instant timestamp, Integer count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getCount() {
        return count;
    }
}
