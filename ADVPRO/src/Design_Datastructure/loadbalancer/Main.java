package Design_Datastructure.loadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BackendServer> servers = new ArrayList<>();
        servers.add(new BackendServer("192.168.1.1", 8080));
        servers.add(new BackendServer("192.168.1.2", 8080));
        servers.add(new BackendServer("192.168.1.3", 8080));

        LoadBalancingStrategy roundRobinStrategy = new RoundRobinStrategy();
        LoadBalancer loadBalancer = new LoadBalancer(roundRobinStrategy);
        servers.forEach(loadBalancer::addServer);

        HealthChecker healthChecker = new HealthChecker(servers);
        healthChecker.start();

        Client client = new Client(loadBalancer, 10);

        for (int i = 0; i < 10; i++) {
            client.sendRequest();
        }

        // Change strategy to LeastConnectionsStrategy
        loadBalancer.setStrategy(new LeastConnectionsStrategy());

        for (int i = 0; i < 10; i++) {
            client.sendRequest();
        }

        client.shutdown();
    }
}
