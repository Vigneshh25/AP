package Design_Datastructure.loadbalancer;

import java.util.List;

public class LeastConnectionsStrategy implements LoadBalancingStrategy {
    @Override
    public BackendServer selectServer(List<BackendServer> servers) {
        BackendServer bestServer = null;
        int minConnections = Integer.MAX_VALUE;
        for (BackendServer server : servers) {
            if (server.isHealthy() && server.getActiveConnections() < minConnections) {
                bestServer = server;
                minConnections = server.getActiveConnections();
            }
        }
        return bestServer;
    }
}
