package ratelimiter;

class ClientRequestInfo {
    long lastRequestTime;
    long requestCount;

    ClientRequestInfo(long lastRequestTime, long requestCount) {
        this.lastRequestTime = lastRequestTime;
        this.requestCount = requestCount;
    }
}