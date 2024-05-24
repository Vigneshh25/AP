package Design_Datastructure.ratelimter.versions;

@FunctionalInterface
public interface RateLimiterTask<T> {
    T execute() throws Exception;
}
