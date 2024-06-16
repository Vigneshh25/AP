package URLShortener;

public interface URLRepository {
    void save(String shortURL, String longURL);
    String find(String shortURL);
}
