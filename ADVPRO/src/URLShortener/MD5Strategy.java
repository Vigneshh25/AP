package URLShortener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Strategy implements ShorteningStrategy {

    @Override
    public String shorten(String longURL) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(longURL.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString().substring(0, 8); // Taking first 8 characters
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
