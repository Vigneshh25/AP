package truecaller.ClientLayer;

import Design_Datastructure.LRUCache;
import truecaller.APILayer.TrueCallerAPI;
import truecaller.DatabaseLayer.InMemoryPhoneNumberRepository;
import truecaller.DatabaseLayer.PhoneNumber;
import truecaller.DatabaseLayer.PhoneNumberRepository;
import truecaller.ServiceLayer.PhoneNumberService;
import truecaller.ServiceLayer.PhoneNumberServiceImpl;

import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        PhoneNumberRepository repository = new InMemoryPhoneNumberRepository();
        LRUCache<String, String> cache = new LRUCache<>(100);
        PhoneNumberService phoneNumberService = new PhoneNumberServiceImpl(repository, cache);
        TrueCallerAPI api = new TrueCallerAPI(phoneNumberService);

        // Adding phone numbers
        api.addPhoneNumber("1234567890", "Alice");
        api.addPhoneNumber("0987654321", "Bob");

        // Adding multiple phone numbers
        api.addPhoneNumbers(Arrays.asList(
                new PhoneNumber("1111111111", "Charlie"),
                new PhoneNumber("2222222222", "Dave")
        ));

        // Searching for phone numbers
        System.out.println("1234567890 belongs to: " + api.searchNumber("1234567890"));
        System.out.println("0987654321 belongs to: " + api.searchNumber("0987654321"));
        System.out.println("1111111111 belongs to: " + api.searchNumber("1111111111"));
        System.out.println("2222222222 belongs to: " + api.searchNumber("2222222222"));

        // Updating a phone number
        api.updatePhoneNumber("1234567890", "Alicia");
        System.out.println("1234567890 now belongs to: " + api.searchNumber("1234567890"));

        // Deleting a phone number
        api.deletePhoneNumber("0987654321");
        System.out.println("0987654321 belongs to: " + api.searchNumber("0987654321"));

        // Listing all phone numbers
        Collection<PhoneNumber> allNumbers = api.listAllPhoneNumbers();
        System.out.println("All phone numbers:");
        for (PhoneNumber phoneNumber : allNumbers) {
            System.out.println(phoneNumber.getNumber() + " belongs to: " + phoneNumber.getName());
        }
        api.deletePhoneNumber("0987654321");

    }
}
