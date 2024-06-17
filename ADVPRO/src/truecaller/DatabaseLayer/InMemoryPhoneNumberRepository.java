package truecaller.DatabaseLayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPhoneNumberRepository implements PhoneNumberRepository {
    private Map<String, PhoneNumber> database = new HashMap<>();

    @Override
    public void save(PhoneNumber phoneNumber) {
        database.put(phoneNumber.getNumber(), phoneNumber);
    }

    @Override
    public PhoneNumber findByNumber(String number) {
        return database.get(number);
    }

    @Override
    public void deleteByNumber(String number) {
        database.remove(number);
    }

    @Override
    public Collection<PhoneNumber> findAll() {
        return database.values();
    }
}
