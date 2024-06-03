package Design_Datastructure.apigateway;

public class Route {
    private String path;
    private String serviceUrl;

    public Route(String path, String serviceUrl) {
        this.path = path;
        this.serviceUrl = serviceUrl;
    }

    // Getters and Setters

    public String getPath() {
        return path;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }
}
