package atm.RepositoryLayer;

import atm.ModelLayer.Account;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account findAccountByNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public void updateAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}
