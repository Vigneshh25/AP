package URLShortener;

public interface Encoder {
    String encode(String longURL);
}

interface Decoder {
    String decode(String shortURL);
}
