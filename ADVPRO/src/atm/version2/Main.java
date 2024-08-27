package atm.version2;

import atm.version2.dispenser.CashDispenser;
import atm.version2.dispenser.FiveHundredNotesDispenser;
import atm.version2.dispenser.OneHundredNotesDispenser;
import atm.version2.dispenser.TwoHundredNotesDispenser;
import atm.version1.repository.Account;
import atm.version1.repository.InMemoryAccountRepository;
import atm.version2.hardware.ATM;
import atm.version2.utils.AccountCreationFactory;
import atm.version2.utils.AccountService;

public class Main {
    public static void main(String[] args) {
        // Setup initial accounts
        InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
        AccountCreationFactory accountFactory = new AccountCreationFactory();

        Account currentAccount = accountFactory.createAccount(AccountType.CURRENT, "123456789", 1000.00);
        Account savingsAccount = accountFactory.createAccount(AccountType.SAVINGS, "987654321", 5000.00);

        accountRepository.updateAccount(currentAccount);
        accountRepository.updateAccount(savingsAccount);

        AccountService accountService = new AccountService(accountRepository);

        // Setup cash dispenser chain
        CashDispenser fiveHundredDispenser = new FiveHundredNotesDispenser();
        CashDispenser twoHundredDispenser = new TwoHundredNotesDispenser();
        CashDispenser oneHundredDispenser = new OneHundredNotesDispenser();

        fiveHundredDispenser.setNextDispenser(twoHundredDispenser);
        twoHundredDispenser.setNextDispenser(oneHundredDispenser);

        ATM atm = new ATM(fiveHundredDispenser, accountService);

        atm.run();
    }
}

