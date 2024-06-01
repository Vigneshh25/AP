package Design_Datastructure.ratelimter.combined;

import java.time.Instant;

public interface RateLimitStrategy {
    boolean hit(String user, Instant timestamp);
}
