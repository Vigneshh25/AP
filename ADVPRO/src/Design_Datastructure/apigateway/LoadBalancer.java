package Design_Datastructure.apigateway;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer {
    private final List<String> serviceUrls;
    private final AtomicInteger index;

    public LoadBalancer(List<String> serviceUrls) {
        this.serviceUrls = serviceUrls;
        this.index = new AtomicInteger(0);
    }

    public String getNextServiceUrl() {
        return serviceUrls.get(index.getAndUpdate(i -> (i + 1) % serviceUrls.size()));
    }
}
