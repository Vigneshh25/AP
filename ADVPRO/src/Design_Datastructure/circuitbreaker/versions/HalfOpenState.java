package Design_Datastructure.circuitbreaker.versions;

public class HalfOpenState extends CircuitBreakerState {
    private int successCount;

    public HalfOpenState(CircuitBreaker circuitBreaker) {
        super(circuitBreaker);
        this.successCount = 0;
    }

    @Override
    public boolean allowRequest() {
        return true;
    }

    @Override
    public void recordSuccess() {
        successCount++;
        if (successCount >= circuitBreaker.getSuccessThreshold()) {
            circuitBreaker.transitionTo(new ClosedState(circuitBreaker));
        }
    }

    @Override
    public void recordFailure() {
        circuitBreaker.transitionTo(new OpenState(circuitBreaker));
    }
}
