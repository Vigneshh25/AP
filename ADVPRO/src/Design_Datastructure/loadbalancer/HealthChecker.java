package Design_Datastructure.loadbalancer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HealthChecker {
    private final List<BackendServer> servers;

    public HealthChecker(List<BackendServer> servers) {
        this.servers = servers;
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (BackendServer server : servers) {
                    boolean isHealthy = pingServer(server);
                    server.setHealthy(isHealthy);
                }
            }
        }, 0, 5000); // Run every 5 seconds
    }

    private boolean pingServer(BackendServer server) {
        // Simulate ping logic
        return true; // Assume always healthy for simplicity
    }
}
