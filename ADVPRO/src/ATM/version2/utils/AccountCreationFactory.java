package ATM.version2.utils;

import ATM.version2.AccountType;
import ATM.version1.repository.Account;

public class AccountCreationFactory {
    public Account createAccount(AccountType type, String accountNumber, double balance) {
        return new Account(accountNumber, balance);
    }
}
