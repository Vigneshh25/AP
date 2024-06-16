package URLShortener;

public class Main {
    public static void main(String[] args) {
        URLRepository urlRepository = new URLRepositoryImpl();
        Cache cache = CacheImpl.getInstance();
        ShorteningStrategyFactory strategyFactory = new ShorteningStrategyFactory();

        URLShortenerService urlShortenerService = new URLShortenerServiceImpl(urlRepository, cache, strategyFactory);

        // Simulating user interactions
        String longURL1 = "https://example.com/some/very/long/url";
        String longURL2 = "https://anotherexample.com/another/very/long/url";

        // Shortening URLs with Base62 strategy
        String shortURL1 = urlShortenerService.shortenURL(longURL1);
        System.out.println("Shortened URL (Base62): " + shortURL1);
        System.out.println("Original URL: " + urlShortenerService.getLongURL(shortURL1));

        // Changing the strategy to MD5
        ((URLShortenerServiceImpl) urlShortenerService).setStrategy("md5");

        // Shortening URLs with MD5 strategy
        String shortURL2 = urlShortenerService.shortenURL(longURL2);
        System.out.println("Shortened URL (MD5): " + shortURL2);
        System.out.println("Original URL: " + urlShortenerService.getLongURL(shortURL2));
    }
}
