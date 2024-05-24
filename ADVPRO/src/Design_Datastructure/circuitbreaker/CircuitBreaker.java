package Design_Datastructure.circuitbreaker;

import java.util.Timer;
import java.util.TimerTask;

public class CircuitBreaker {
    private static final int DEFAULT_FAILURE_THRESHOLD = 3;
    private static final long DEFAULT_COOLDOWN_PERIOD_MS = 5000; // 5 seconds

    private final int failureThreshold;
    private final long cooldownPeriodMs;

    private int consecutiveFailures;
    private boolean circuitOpen;

    public CircuitBreaker() {
        this(DEFAULT_FAILURE_THRESHOLD, DEFAULT_COOLDOWN_PERIOD_MS);
    }

    public CircuitBreaker(int failureThreshold, long cooldownPeriodMs) {
        this.failureThreshold = failureThreshold;
        this.cooldownPeriodMs = cooldownPeriodMs;
        this.consecutiveFailures = 0;
        this.circuitOpen = false;
    }

    public synchronized boolean allowRequest() {
        // Circuit is open, reject request
        return !circuitOpen;
    }

    public synchronized void incrementFailureCount() {
        consecutiveFailures++;
        if (consecutiveFailures >= failureThreshold) {
            openCircuit();
        }
    }

    public synchronized void reset() {
        consecutiveFailures = 0;
        circuitOpen = false;
    }

    private synchronized void openCircuit() {
        circuitOpen = true;
        // Schedule a task to close the circuit after the cooldown period
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reset();
                timer.cancel();
            }
        }, cooldownPeriodMs);
    }
}
