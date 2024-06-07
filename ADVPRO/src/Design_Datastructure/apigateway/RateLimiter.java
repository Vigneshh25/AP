package Design_Datastructure.apigateway;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final ConcurrentHashMap<String, ClientRequestInfo> requestCounts;
    private final long limit;
    private final long timeFrameMillis;

    public RateLimiter(long limit, long timeFrame, TimeUnit timeUnit) {
        this.requestCounts = new ConcurrentHashMap<>();
        this.limit = limit;
        this.timeFrameMillis = timeUnit.toMillis(timeFrame)/10000;
    }

    public synchronized boolean isAllowed(String clientId) {
        long currentTime = System.currentTimeMillis();
        ClientRequestInfo clientInfo = requestCounts.get(clientId);

        if (clientInfo == null) {
            clientInfo = new ClientRequestInfo(currentTime, 1);
            requestCounts.put(clientId, clientInfo);
            return true;
        }

        synchronized (clientInfo) {
            if ((currentTime - clientInfo.lastRequestTime) >= timeFrameMillis) {
                clientInfo.lastRequestTime = currentTime;
                clientInfo.requestCount = 1;
                return true;
            } else {
                if (clientInfo.requestCount < limit) {
                    clientInfo.requestCount++;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
