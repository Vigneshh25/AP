package URLShortener;

public class Base62Strategy implements ShorteningStrategy {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public String shorten(String longURL) {
        int hashCode = longURL.hashCode();
        StringBuilder encoded = new StringBuilder();
        while (hashCode > 0) {
            encoded.append(BASE62.charAt(hashCode % 62));
            hashCode /= 62;
        }
        return encoded.toString();
    }
}
