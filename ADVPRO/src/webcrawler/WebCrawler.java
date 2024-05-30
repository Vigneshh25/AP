package webcrawler;

import java.io.IOException;
import java.util.Set;

public class WebCrawler {
    private URLFrontier urlFrontier;
    private Downloader downloader;
    private Parser parser;
    private Storage storage;
    private URLNotifier notifier;

    public WebCrawler() {
        this.urlFrontier = URLFrontier.getInstance();
        this.downloader = DownloaderFactory.getDownloader();
        this.parser = new Parser();
        this.storage = new Storage();
        this.notifier = new URLNotifier();
    }

    public void startCrawling(String startUrl) {
        urlFrontier.addUrl(startUrl);
        notifier.addObserver(new URLObserver());

        while (!urlFrontier.isEmpty()) {
            String url = urlFrontier.getNextUrl();
            try {
                String content = downloader.download(url);
                storage.saveData(parser.parseData(content), "data.txt");
                Set<String> links = parser.parseLinks(content);
                for (String link : links) {
                    urlFrontier.addUrl(link);
                    notifier.notifyObservers(link);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        crawler.startCrawling("https://www.irctc.co.in/nget/train-search");
    }
}
