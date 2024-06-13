package distributedcache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface HashFunction {
    int hash(String key);
}

