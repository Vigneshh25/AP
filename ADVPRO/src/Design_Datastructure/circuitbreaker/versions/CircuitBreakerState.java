package Design_Datastructure.circuitbreaker.versions;

public abstract class CircuitBreakerState {
    protected final CircuitBreaker circuitBreaker;

    protected CircuitBreakerState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public abstract boolean allowRequest();

    public abstract void recordSuccess();

    public abstract void recordFailure();
}

