package Design_Datastructure.circuitbreaker;


import java.util.*;

public class RequestQueue {
    private final Queue<String> queue;

    public RequestQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueueRequest(String request) {
        queue.offer(request);
    }

    public synchronized String dequeueRequest() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}