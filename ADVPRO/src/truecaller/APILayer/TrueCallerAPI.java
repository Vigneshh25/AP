package truecaller.APILayer;

import truecaller.DatabaseLayer.PhoneNumber;
import truecaller.ServiceLayer.PhoneNumberService;

import java.util.Collection;
import java.util.List;

public class TrueCallerAPI {
    private final PhoneNumberService phoneNumberService;

    public TrueCallerAPI(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public String searchNumber(String number) {
        return phoneNumberService.getNameByNumber(number);
    }

    public void addPhoneNumber(String number, String name) {
        phoneNumberService.addPhoneNumber(number, name);
    }

    public void addPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        phoneNumberService.addPhoneNumbers(phoneNumbers);
    }

    public void deletePhoneNumber(String number) {
        phoneNumberService.deletePhoneNumber(number);
    }

    public void updatePhoneNumber(String number, String newName) {
        phoneNumberService.updatePhoneNumber(number, newName);
    }

    public Collection<PhoneNumber> listAllPhoneNumbers() {
        return phoneNumberService.listAllPhoneNumbers();
    }
}

