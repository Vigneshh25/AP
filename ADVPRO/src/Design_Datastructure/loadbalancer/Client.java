package Design_Datastructure.loadbalancer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private final LoadBalancer loadBalancer;
    private final ExecutorService executorService;

    public Client(LoadBalancer loadBalancer, int threadPoolSize) {
        this.loadBalancer = loadBalancer;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void sendRequest() {
        executorService.execute(() -> {
            BackendServer server = loadBalancer.getServer();
            if (server != null) {
                server.incrementActiveConnections();
                try {
                    // Simulate request processing
                    System.out.println("Request sent to server: " + server.getIp());
                } finally {
                    server.decrementActiveConnections();
                }
            } else {
                System.out.println("No available server to handle the request.");
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
