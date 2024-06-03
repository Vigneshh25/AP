package Design_Datastructure.apigateway;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Route> routes = Arrays.asList(
            new Route("/api/service1", "http://service1.local"),
            new Route("/api/service2", "http://service2.local"),
            new Route("/api/service3", "http://service3.local")
        );

        List<String> serviceUrls = Arrays.asList(
            "http://service1.local",
            "http://service2.local",
            "http://service3.local"
        );

        LoadBalancer loadBalancer = new LoadBalancer(serviceUrls);
        ApiGateway apiGateway = new ApiGateway(routes, loadBalancer, 100, 1);

        // Simulate client requests
        apiGateway.handleRequest("/api/service1", "valid-token", "client1", "admin");
        apiGateway.handleRequest("/api/service2", "invalid-token", "client1", "admin");
        apiGateway.handleRequest("/api/service3", "valid-token", "client2", "user");

        apiGateway.shutdown();
    }
}
