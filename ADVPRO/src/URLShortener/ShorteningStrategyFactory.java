package URLShortener;

public class ShorteningStrategyFactory {
    public ShorteningStrategy getStrategy(String strategyType) {
        switch (strategyType.toLowerCase()) {
            case "base62":
                return new Base62Strategy();
            case "md5":
                return new MD5Strategy();
            default:
                throw new IllegalArgumentException("Unknown strategy type");
        }
    }
}
