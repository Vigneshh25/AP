package Design_Datastructure.apigateway;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RequestHandler {
    private final ConcurrentHashMap<String, Route> routeMap;

    public RequestHandler(List<Route> routes) {
        routeMap = new ConcurrentHashMap<>();
        for (Route route : routes) {
            routeMap.put(route.getPath(), route);
        }
    }

    public void handleRequest(String path) {
        Route route = routeMap.get(path);
        if (route != null) {
            forwardRequest(route);
        } else {
            System.out.println("No route found for path: " + path);
        }
    }

    private void forwardRequest(Route route) {
        // Simulate forwarding the request to the backend service
        System.out.println("Forwarding request to: " + route.getServiceUrl());
    }
}
