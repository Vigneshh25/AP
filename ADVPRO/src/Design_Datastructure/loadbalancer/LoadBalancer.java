package Design_Datastructure.loadbalancer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoadBalancer {
    private final List<BackendServer> servers;
    private LoadBalancingStrategy strategy;

    public LoadBalancer(LoadBalancingStrategy strategy) {
        this.servers = new CopyOnWriteArrayList<>();
        this.strategy = strategy;
    }

    public BackendServer getServer() {
        return strategy.selectServer(servers);
    }

    public void addServer(BackendServer server) {
        servers.add(server);
    }

    public void removeServer(BackendServer server) {
        servers.remove(server);
    }

    public void setStrategy(LoadBalancingStrategy strategy) {
        this.strategy = strategy;
    }
}
