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

        ApiGateway apiGateway = new ApiGateway(routes, 10, 60);

        // Simulate client requests
        for(int i=1;i<=400;i++)
            apiGateway.handleRequest("/api/service1", "valid-token", "client1", "admin");
//        apiGateway.handleRequest("/api/service2", "invalid-token", "client1", "admin");
//        apiGateway.handleRequest("/api/service3", "valid-token", "client2", "user");

        apiGateway.shutdown();
    }
}
