package webcrawler;

import java.util.LinkedList;
import java.util.Queue;

public class URLFrontier {
    private static URLFrontier instance;
    private Queue<String> urlQueue;

    private URLFrontier() {
        urlQueue = new LinkedList<>();
    }

    public static synchronized URLFrontier getInstance() {
        if (instance == null) {
            instance = new URLFrontier();
        }
        return instance;
    }

    public synchronized void addUrl(String url) {
        urlQueue.add(url);
    }

    public synchronized String getNextUrl() {
        return urlQueue.poll();
    }

    public synchronized boolean isEmpty() {
        return urlQueue.isEmpty();
    }
}
