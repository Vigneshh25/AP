package Design_Datastructure.loadbalancer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HealthChecker {
    private final List<BackendServer> servers;

    public HealthChecker(List<BackendServer> servers) {
        this.servers = servers;
        scheduleHealthCheck();
    }

    private void scheduleHealthCheck() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            for (BackendServer server : servers) {
                boolean isHealthy = pingServer(server);
                System.out.println(isHealthy);
                server.setHealthy(isHealthy);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private boolean pingServer(BackendServer server) {
        // Simulate ping logic
        return true; // Assume always healthy for simplicity
    }
}
