package Design_Datastructure.circuitbreaker.versions;

public class Metrics {
    private int successCount;
    private int failureCount;

    public synchronized void recordSuccess() {
        successCount++;
    }

    public synchronized void recordFailure() {
        failureCount++;
    }

    public synchronized int getSuccessCount() {
        return successCount;
    }

    public synchronized int getFailureCount() {
        return failureCount;
    }

    public synchronized void reset() {
        successCount = 0;
        failureCount = 0;
    }
}
