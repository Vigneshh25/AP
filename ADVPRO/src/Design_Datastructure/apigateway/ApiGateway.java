package Design_Datastructure.apigateway;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ApiGateway {
    private final RequestHandler requestHandler;
    private final AuthMiddleware authMiddleware;
    private final RateLimiter rateLimiter;
    private final ExecutorService executorService;

    public ApiGateway(List<Route> routes, long rateLimit, long timeFrame) {
        this.requestHandler = new RequestHandler(routes);
        this.authMiddleware = new AuthMiddleware();
        this.rateLimiter = new RateLimiter(rateLimit, timeFrame, TimeUnit.SECONDS);
        this.executorService = Executors.newCachedThreadPool();
    }

    public void handleRequest(String path, String token, String clientId, String role) {
        executorService.execute(() -> {
            if (!authMiddleware.authenticate(token)) {
                System.out.println("Authentication failed for token: " + token);
                return;
            }

            if (!authMiddleware.authorize(path, role)) {
                System.out.println("Authorization failed for path: " + path);
                return;
            }

            if (!rateLimiter.isAllowed(clientId)) {
//                System.out.println("Rate limit exceeded for client: " + clientId);
                return;
            }

            requestHandler.handleRequest(path);
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
