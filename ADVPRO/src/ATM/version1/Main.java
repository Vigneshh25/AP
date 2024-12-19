package ATM.version1;

import ATM.version1.devices.*;
import ATM.version2.dispenser.CashDispenser;
import ATM.version2.dispenser.FiveHundredNotesDispenser;
import ATM.version2.dispenser.OneHundredNotesDispenser;
import ATM.version2.dispenser.TwoHundredNotesDispenser;
import ATM.version1.repository.Account;
import ATM.version1.repository.AccountService;
import ATM.version1.repository.InMemoryAccountRepository;

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
