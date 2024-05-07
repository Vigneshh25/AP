import java.util.*;

class CircuitBreaker {
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

class HealthChecker {
    private final Map<String, Boolean> serviceStatus;

    public HealthChecker() {
        this.serviceStatus = new HashMap<>();
    }

    public synchronized boolean isServiceHealthy(String serviceName) {
        return serviceStatus.getOrDefault(serviceName, true); // Default to healthy if unknown
    }

    public synchronized void updateServiceStatus(String serviceName, boolean isHealthy) {
        serviceStatus.put(serviceName, isHealthy);
    }

    // Simulate periodic health checks (for demo purposes)
    public void startHealthChecks() {
        Random random = new Random();
        Thread healthCheckThread = new Thread(() -> {
            while (true) {
                for (String serviceName : serviceStatus.keySet()) {
                    boolean isHealthy = random.nextBoolean(); // Simulate health status
                    updateServiceStatus(serviceName, isHealthy);
                    System.out.println(serviceName + " is healthy: " + isHealthy);
                }
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds between health checks
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        healthCheckThread.start();
    }
}

class RequestQueue {
    private final Queue<String> queue;

    public RequestQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueueRequest(String request) {
        queue.offer(request);
    }

    public synchronized String dequeueRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

public class CircuitBreakerService {
    private final CircuitBreaker circuitBreaker;
    private final HealthChecker healthChecker;
    private final RequestQueue requestQueue;

    public CircuitBreakerService() {
        this.circuitBreaker = new CircuitBreaker();
        this.healthChecker = new HealthChecker();
        this.requestQueue = new RequestQueue();

        // Start health checks
        healthChecker.startHealthChecks();
    }

    public static void main(String[] args) {
        CircuitBreakerService service = new CircuitBreakerService();
        // Simulate processing requests
        service.processRequest("BackendServiceA", "Request1");
        service.processRequest("BackendServiceA", "Request2");
        service.processRequest("BackendServiceA", "Request3");
    }

    public void processRequest(String serviceName, String request) {
        if (circuitBreaker.allowRequest() && healthChecker.isServiceHealthy(serviceName)) {
            // Forward request to the backend service
            System.out.println("Processing request for " + serviceName + ": " + request);
            // Simulated backend call
            boolean success = true; // Assume success for demo
            if (!success) {
                circuitBreaker.incrementFailureCount();
            }
        } else {
            // Queue the request if circuit is open or service is unhealthy
            requestQueue.enqueueRequest(request);
            System.out.println("Request queued for " + serviceName + ": " + request);
        }
    }
}
