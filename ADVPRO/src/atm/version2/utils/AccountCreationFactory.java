package atm.version2.utils;

import atm.version2.AccountType;
import atm.version1.repository.Account;

public class AccountCreationFactory {
    public Account create(AccountType type, String accountNumber, double balance) {
        return new Account(accountNumber, balance);
    }
}
