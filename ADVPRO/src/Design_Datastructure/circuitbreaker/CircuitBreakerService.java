package Design_Datastructure.circuitbreaker;

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
