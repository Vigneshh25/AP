package bikerental.service;

import bikerental.model.Customer;
import bikerental.repository.UserRepository;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // UserService Class
public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = UserRepository.getInstance();
    }

    public void addCustomer(Customer customer) {
        userRepository.addCustomer(customer);
    }

    public double getCustomerBalance(String customerId) {
        Customer customer = userRepository.getCustomer(customerId);
        if (customer != null) {
            return customer.getBalance();
        }
        return 0.0;
    }
}
