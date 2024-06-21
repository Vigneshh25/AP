package bikerental;

import java.util.HashMap;
import java.util.Map;

/** * Created by Vignesh.V on 21/06/24. */ // UserRepository Singleton Class
class UserRepository {
    private static UserRepository userRepository;
    private Map<String, Customer> customers = new HashMap<>();

    private UserRepository() {
    }

    public static synchronized UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }
}
