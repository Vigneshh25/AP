package Design_Datastructure.circuitbreaker.versions;

public class ClosedState extends CircuitBreakerState {
    public ClosedState(CircuitBreaker circuitBreaker) {
        super(circuitBreaker);
    }

    @Override
    public boolean allowRequest() {
        return true;
    }

    @Override
    public void recordSuccess() {
        circuitBreaker.getMetrics().recordSuccess();
    }

    @Override
    public void recordFailure() {
        circuitBreaker.getMetrics().recordFailure();
        if (circuitBreaker.getMetrics().getFailureCount() >= circuitBreaker.getFailureThreshold()) {
            circuitBreaker.transitionTo(new OpenState(circuitBreaker));
        }
    }
}
