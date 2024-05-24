package ParkingLot.Parking;

import java.util.HashMap;
import java.util.Map;

public class UserRegistrationModule {
    private final Map<String, String> userContacts;
    private final Map<String, String> userPreferences;

    public UserRegistrationModule() {
        this.userContacts = new HashMap<>();
        this.userPreferences = new HashMap<>();
    }

    public void registerUser(String username, String contactDetails, String preferences) {
        userContacts.put(username, contactDetails);
        userPreferences.put(username, preferences);
    }

    public String getContactDetails(String username) {
        return userContacts.get(username);
    }

    public String getPreferences(String username) {
        return userPreferences.get(username);
    }
}
