package URLShortener;

public class URLShortenerServiceImpl implements URLShortenerService {
    private final URLRepository urlRepository;
    private final Cache cache;
    private final ShorteningStrategyFactory strategyFactory;
    private ShorteningStrategy strategy;

    public URLShortenerServiceImpl(URLRepository urlRepository, Cache cache, ShorteningStrategyFactory strategyFactory) {
        this.urlRepository = urlRepository;
        this.cache = cache;
        this.strategyFactory = strategyFactory;
        this.strategy = strategyFactory.getStrategy("base62"); // default strategy
    }

    public void setStrategy(String strategyType) {
        this.strategy = strategyFactory.getStrategy(strategyType);
    }

    @Override
    public String shortenURL(String longURL) {
        String shortURL = strategy.shorten(longURL);
        urlRepository.save(shortURL, longURL);
        return shortURL;
    }

    @Override
    public String getLongURL(String shortURL) {
        String longURL = cache.get(shortURL);
        if (longURL == null) {
            longURL = urlRepository.find(shortURL);
            if (longURL != null) {
                cache.put(shortURL, longURL);
            }
        }
        return longURL;
    }
}
