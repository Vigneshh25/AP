package Design_Datastructure.loadbalancer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BackendServer {
    private final String ip;
    private final int port;
    private final AtomicBoolean isHealthy;
    private final AtomicInteger activeConnections;

    public BackendServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.isHealthy = new AtomicBoolean(true);
        this.activeConnections = new AtomicInteger(0);
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean isHealthy() {
        return isHealthy.get();
    }

    public void setHealthy(boolean healthy) {
        isHealthy.set(healthy);
    }

    public int getActiveConnections() {
        return activeConnections.get();
    }

    public void incrementActiveConnections() {
        activeConnections.incrementAndGet();
    }

    public void decrementActiveConnections() {
        activeConnections.decrementAndGet();
    }
}

