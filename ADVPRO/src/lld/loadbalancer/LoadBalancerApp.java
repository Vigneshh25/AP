package lld.loadbalancer;

import java.util.ArrayList;
import java.util.List;

// Server class
class Server {
    private String serverId;
    private boolean isHealthy;
    private int activeConnections;

    public Server(String serverId) {
        this.serverId = serverId;
        this.isHealthy = true;
        this.activeConnections = 0;
    }

    public String getServerId() {
        return serverId;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public synchronized int getActiveConnections() {
        return activeConnections;
    }

    public synchronized void incrementConnections() {
        activeConnections++;
    }

    public synchronized void decrementConnections() {
        if (activeConnections > 0) {
            activeConnections--;
        }
    }
}

// LoadBalancer class (Singleton)
class LoadBalancer {
    private static LoadBalancer instance;
    private List<Server> servers;
    private LoadBalancingStrategy strategy;

    private LoadBalancer() {
        this.servers = new ArrayList<>();
    }

    public static synchronized LoadBalancer getInstance() {
        if (instance == null) {
            instance = new LoadBalancer();
        }
        return instance;
    }

    public synchronized void addServer(Server server) {
        servers.add(server);
    }

    public synchronized void removeServer(Server server) {
        servers.remove(server);
    }

    public synchronized Server getServer(Request request) {
        return strategy.getServer(servers, request);
    }

    public synchronized void setLoadBalancingStrategy(LoadBalancingStrategy strategy) {
        this.strategy = strategy;
    }
}

// LoadBalancingStrategy interface
interface LoadBalancingStrategy {
    Server getServer(List<Server> servers, Request request);
}

// RoundRobinStrategy class
class RoundRobinStrategy implements LoadBalancingStrategy {
    private int currentIndex;

    public RoundRobinStrategy() {
        this.currentIndex = 0;
    }

    @Override
    public synchronized Server getServer(List<Server> servers, Request request) {
        int totalServers = servers.size();
        if (totalServers == 0) {
            throw new IllegalStateException("No servers available");
        }

        int attempts = 0;
        while (attempts < totalServers) {
            Server server = servers.get(currentIndex);
            currentIndex = (currentIndex + 1) % totalServers;
            if (server.isHealthy()) {
                return server;
            }
            attempts++;
        }
        throw new IllegalStateException("No healthy servers available");
    }
}

// LeastConnectionsStrategy class
class LeastConnectionsStrategy implements LoadBalancingStrategy {
    @Override
    public Server getServer(List<Server> servers, Request request) {
        Server selectedServer = null;
        int minConnections = Integer.MAX_VALUE;

        for (Server server : servers) {
            if (server.isHealthy() && server.getActiveConnections() < minConnections) {
                minConnections = server.getActiveConnections();
                selectedServer = server;
            }
        }

        if (selectedServer == null) {
            throw new IllegalStateException("No healthy servers available");
        }
        return selectedServer;
    }
}

// Request class
class Request {
    // Request attributes and methods
}

// Main Class
public class LoadBalancerApp {
    public static void main(String[] args) {
        // Create servers
        Server server1 = new Server("server1");
        Server server2 = new Server("server2");

        // Create load balancer
        LoadBalancer loadBalancer = LoadBalancer.getInstance();
        loadBalancer.addServer(server1);
        loadBalancer.addServer(server2);

        // Set load balancing strategy
        LoadBalancingStrategy leastConnectionsStrategy = new LeastConnectionsStrategy();
        loadBalancer.setLoadBalancingStrategy(leastConnectionsStrategy);

        // Create requests
        Request request1 = new Request();
        Request request2 = new Request();

        // Get server for request1
        Server selectedServer1 = loadBalancer.getServer(request1);
        System.out.println("Selected server for request1: " + selectedServer1.getServerId());
        selectedServer1.incrementConnections();

        // Get server for request2
        Server selectedServer2 = loadBalancer.getServer(request2);
        System.out.println("Selected server for request2: " + selectedServer2.getServerId());
        selectedServer2.incrementConnections();

        // Simulate some processing and decrement connections
        selectedServer1.decrementConnections();
        selectedServer2.decrementConnections();
    }
}
