package Design_Datastructure.circuitbreaker.versions;

public class OpenState extends CircuitBreakerState {
    private final long startTime;

    public OpenState(CircuitBreaker circuitBreaker) {
        super(circuitBreaker);
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean allowRequest() {
        if (System.currentTimeMillis() - startTime > circuitBreaker.getTimeout()) {
            circuitBreaker.transitionTo(new HalfOpenState(circuitBreaker));
            return true;
        }
        return false;
    }

    @Override
    public void recordSuccess() {
        // No-op
    }

    @Override
    public void recordFailure() {
        // No-op
    }
}
