package Design_Datastructure.circuitbreaker;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HealthChecker {
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