package Design_Datastructure.circuitbreaker.versions;

public class CircuitBreaker {
    private CircuitBreakerState state;
    private final int failureThreshold;
    private final long timeout;
    private final int successThreshold;
    private final Metrics metrics;

    public CircuitBreaker(int failureThreshold, long timeout, int successThreshold) {
        this.failureThreshold = failureThreshold;
        this.timeout = timeout;
        this.successThreshold = successThreshold;
        this.metrics = new Metrics();
        this.state = new ClosedState(this);
    }

    public synchronized boolean allowRequest() {
        return state.allowRequest();
    }

    public synchronized void recordSuccess() {
        state.recordSuccess();
    }

    public synchronized void recordFailure() {
        state.recordFailure();
    }

    public void transitionTo(CircuitBreakerState newState) {
        this.state = newState;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public long getTimeout() {
        return timeout;
    }

    public int getSuccessThreshold() {
        return successThreshold;
    }
}
