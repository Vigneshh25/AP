package truecaller.DatabaseLayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface PhoneNumberRepository {
    void save(PhoneNumber phoneNumber);
    PhoneNumber findByNumber(String number);
    void deleteByNumber(String number);
    Collection<PhoneNumber> findAll();
}

