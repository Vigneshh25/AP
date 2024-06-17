package truecaller.ServiceLayer;

import Design_Datastructure.LRUCache;
import truecaller.DatabaseLayer.PhoneNumber;
import truecaller.DatabaseLayer.PhoneNumberRepository;

import java.util.Collection;
import java.util.List;

public class PhoneNumberServiceImpl implements PhoneNumberService {
    private final PhoneNumberRepository repository;
    private final LRUCache<String, String> cache;

    public PhoneNumberServiceImpl(PhoneNumberRepository repository, LRUCache<String, String> cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public String getNameByNumber(String number) {
        String name = cache.get(number);
        if (name == null) {
            PhoneNumber phoneNumber = repository.findByNumber(number);
            if (phoneNumber != null) {
                name = phoneNumber.getName();
                cache.put(number, name);
            }
        }
        return name;
    }

    @Override
    public void addPhoneNumber(String number, String name) {
        PhoneNumber phoneNumber = new PhoneNumber(number, name);
        repository.save(phoneNumber);
        cache.put(number, name);
    }

    @Override
    public void addPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        for (PhoneNumber phoneNumber : phoneNumbers) {
            repository.save(phoneNumber);
            cache.put(phoneNumber.getNumber(), phoneNumber.getName());
        }
    }

    @Override
    public void deletePhoneNumber(String number) {
        repository.deleteByNumber(number);
        cache.remove(number);
    }

    @Override
    public void updatePhoneNumber(String number, String newName) {
        PhoneNumber phoneNumber = repository.findByNumber(number);
        if (phoneNumber != null) {
            phoneNumber.setName(newName);
            repository.save(phoneNumber);
            cache.put(number, newName);
        }
    }

    @Override
    public Collection<PhoneNumber> listAllPhoneNumbers() {
        return repository.findAll();
    }
}
