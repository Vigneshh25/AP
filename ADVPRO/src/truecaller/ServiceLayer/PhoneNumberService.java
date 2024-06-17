package truecaller.ServiceLayer;


import truecaller.DatabaseLayer.PhoneNumber;

import java.util.Collection;
import java.util.List;

public interface PhoneNumberService {
    String getNameByNumber(String number);
    void addPhoneNumber(String number, String name);
    void addPhoneNumbers(List<PhoneNumber> phoneNumbers);
    void deletePhoneNumber(String number);
    void updatePhoneNumber(String number, String newName);
    Collection<PhoneNumber> listAllPhoneNumbers();
}



