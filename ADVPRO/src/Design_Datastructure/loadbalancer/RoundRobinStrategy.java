package Design_Datastructure.loadbalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinStrategy implements LoadBalancingStrategy {
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public BackendServer selectServer(List<BackendServer> servers) {
        int serverCount = servers.size();
        for (int i = 0; i < serverCount; i++) {
            BackendServer server = servers.get(currentIndex.getAndIncrement() % serverCount);
            if (server.isHealthy()) {
                return server;
            }
        }
        return null; // No healthy server available
    }
}
