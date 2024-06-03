package Design_Datastructure.loadbalancer;

import java.util.List;

public interface LoadBalancingStrategy {
    BackendServer selectServer(List<BackendServer> servers);
}

