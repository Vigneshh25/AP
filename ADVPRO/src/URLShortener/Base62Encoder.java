package URLShortener;

public class Base62Encoder implements Encoder {
    // Base62 characters
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public String encode(String longURL) {
        // Simple hash-based encoding
        int hashCode = longURL.hashCode();
        StringBuilder encoded = new StringBuilder();
        while (hashCode > 0) {
            encoded.append(BASE62.charAt(hashCode % 62));
            hashCode /= 62;
        }
        return encoded.toString();
    }
}

class Base62Decoder implements Decoder {
    @Override
    public String decode(String shortURL) {
        return "";
    }
    // Implementation of decode logic
}
