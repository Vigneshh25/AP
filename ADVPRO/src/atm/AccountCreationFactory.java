package atm;

import atm.ModelLayer.Account;
import atm.ModelLayer.AccountType;
import atm.ModelLayer.CurrentAccount;
import atm.ModelLayer.SavingsAccount;

public class AccountCreationFactory {
    public Account create(AccountType accountType, String accountNumber, double initialBalance) {
        switch (accountType) {
            case CURRENT:
                return new CurrentAccount(accountNumber, initialBalance);
            case SAVINGS:
                return new SavingsAccount(accountNumber, initialBalance);
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }
}
