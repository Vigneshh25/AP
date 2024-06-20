package Problems;

/**
 * Created by Vignesh.V on 20/06/24.
 */

import java.net.*;
import java.util.*;

class WebCrawlerWithSameHostname extends WebCrawler {
    private final String startHostname;

    public WebCrawlerWithSameHostname(HtmlParser htmlParser, String startUrl) {
        super(htmlParser);
        this.startHostname = getHostname(startUrl);
    }

    @Override
    public List<String> crawl(String startUrl) {
        if (!getHostname(startUrl).equals(startHostname)) {
            return new ArrayList<>(); // Return empty list if startUrl's hostname doesn't match
        }
        return super.crawl(startUrl);
    }

    @Override
    protected void crawlHelper(String url) {
        if (!getHostname(url).equals(startHostname)) {
            return; // Skip crawling if URL's hostname doesn't match startHostname
        }
        super.crawlHelper(url);
    }

    private String getHostname(String url) {
        try {
            URL parsedUrl = new URL(url);
            return parsedUrl.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }
}

public class WebCrawlerWithSameHostnameMain {
    public static void main(String[] args) {
        // Mock data for testing
        Map<String, List<String>> urlMap = new HashMap<>();
        urlMap.put("http://example.com", Arrays.asList("http://example.com/page1", "http://example.com/page2"));
        urlMap.put("http://example.com/page1", Arrays.asList("http://example.com", "http://example.com/page3"));
        urlMap.put("http://example.com/page2", Collections.singletonList("http://example.com/page4"));
        urlMap.put("http://example.com/page3", Arrays.asList("http://example.com/page1", "http://example.com/page4"));
        urlMap.put("http://example.com/page4", Collections.emptyList());

        HtmlParser htmlParser = new MockHtmlParser(urlMap);
        WebCrawlerWithSameHostname crawler = new WebCrawlerWithSameHostname(htmlParser, "http://example.com");

        List<String> crawledUrls = crawler.crawl("http://example.com");

        System.out.println("Crawled URLs with same hostname:");
        for (String url : crawledUrls) {
            System.out.println(url);
        }
    }
}

