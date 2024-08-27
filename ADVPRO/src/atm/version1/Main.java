package atm.version1;

import atm.version1.devices.*;
import atm.version2.dispenser.CashDispenser;
import atm.version2.dispenser.FiveHundredNotesDispenser;
import atm.version2.dispenser.OneHundredNotesDispenser;
import atm.version2.dispenser.TwoHundredNotesDispenser;
import atm.version1.repository.Account;
import atm.version1.repository.AccountService;
import atm.version1.repository.InMemoryAccountRepository;

public class Main {
    public static void main(String[] args) {
        // Setup initial accounts
        InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
        accountRepository.updateAccount(new Account("123456789", 1000.00));
        accountRepository.updateAccount(new Account("987654321", 5000.00));

        AccountService accountService = new AccountService(accountRepository);

        // Initialize hardware components
        CardReader cardReader = new CardReaderImpl();
        Keypad keypad = new KeypadImpl();
        Screen screen = new ScreenImpl();
        DepositSlot depositSlot = new DepositSlotImpl();
        CashDispenser fiveHundredNotesDispenser = new FiveHundredNotesDispenser();
        CashDispenser oneHundredNotesDispenser = new OneHundredNotesDispenser();
        CashDispenser twoHundredNotesDispenser = new TwoHundredNotesDispenser();
        fiveHundredNotesDispenser.setNextDispenser(twoHundredNotesDispenser);
        twoHundredNotesDispenser.setNextDispenser(oneHundredNotesDispenser);

        // Create ATM instance
        ATM atm = new ATM(cardReader, keypad, screen, fiveHundredNotesDispenser, depositSlot, accountService);
        atm.run();
    }
}
