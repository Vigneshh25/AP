package Problems;

/**
 * Created by Vignesh.V on 20/06/24.
 */
import java.util.*;
import java.util.concurrent.*;

interface HtmlParser {
    List<String> getUrls(String url);
}

class WebCrawler {
    private final Set<String> visitedUrls;
    private final ExecutorService executorService;
    private final HtmlParser htmlParser;

    public WebCrawler(HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
        this.visitedUrls = new HashSet<>();
        this.executorService = Executors.newFixedThreadPool(10); // Adjust thread pool size as needed
    }

    public List<String> crawl(String startUrl) {
        crawlHelper(startUrl);
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(visitedUrls);
    }

    void crawlHelper(String url) {
        if (visitedUrls.contains(url)) {
            return;
        }

        visitedUrls.add(url);
        List<Future<Void>> futures = new ArrayList<>();

        for (String nextUrl : htmlParser.getUrls(url)) {
            if (!visitedUrls.contains(nextUrl)) {
                futures.add(executorService.submit(() -> {
                    crawlHelper(nextUrl);
                    return null;
                }));
            }
        }

        for (Future<Void> future : futures) {
            try {
                future.get(); // Wait for each task to complete
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

// Mock implementation of HtmlParser for demonstration
class MockHtmlParser implements HtmlParser {
    private final Map<String, List<String>> urlMap;

    public MockHtmlParser(Map<String, List<String>> urlMap) {
        this.urlMap = urlMap;
    }

    @Override
    public List<String> getUrls(String url) {
        return urlMap.getOrDefault(url, Collections.emptyList());
    }
}

public class WebCrawlerMain {
    public static void main(String[] args) {
        // Mock data for testing
        Map<String, List<String>> urlMap = new HashMap<>();
        urlMap.put("http://example.com", Arrays.asList("http://example.com/page1", "http://example.com/page2"));
        urlMap.put("http://example.com/page1", Arrays.asList("http://example.com", "http://example.com/page3"));
        urlMap.put("http://example.com/page2", Collections.singletonList("http://example.com/page4"));
        urlMap.put("http://example.com/page3", Arrays.asList("http://example.com/page1", "http://example.com/page4"));
        urlMap.put("http://example.com/page4", Collections.emptyList());

        HtmlParser htmlParser = new MockHtmlParser(urlMap);
        WebCrawler crawler = new WebCrawler(htmlParser);

        String startUrl = "http://example.com";
        List<String> crawledUrls = crawler.crawl(startUrl);

        System.out.println("Crawled URLs:");
        for (String url : crawledUrls) {
            System.out.println(url);
        }
    }
}

