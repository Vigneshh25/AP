package URLShortener;

public interface URLShortenerService {
    String shortenURL(String longURL);
    String getLongURL(String shortURL);
}
