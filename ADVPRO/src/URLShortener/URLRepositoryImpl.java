package URLShortener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class URLRepositoryImpl implements URLRepository {
    private final Map<String, String> database = new ConcurrentHashMap<>();

    @Override
    public void save(String shortURL, String longURL) {
        database.put(shortURL, longURL);
    }

    @Override
    public String find(String shortURL) {
        return database.get(shortURL);
    }
}
