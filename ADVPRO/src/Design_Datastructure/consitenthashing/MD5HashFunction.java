package Design_Datastructure.consitenthashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashFunction implements HashFunction {

    @Override
    public int hash(String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(key.getBytes(StandardCharsets.UTF_8));
            return Math.abs(((digest[3] & 0xFF) << 24)
                          | ((digest[2] & 0xFF) << 16)
                          | ((digest[1] & 0xFF) << 8)
                          | (digest[0] & 0xFF));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
