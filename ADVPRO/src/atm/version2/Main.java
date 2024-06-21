package atm.version2;

import atm.version1.devices.*;
import atm.version1.repository.Account;
import atm.version1.repository.InMemoryAccountRepository;
import atm.version1.dispenser.CashDispenser;
import atm.version1.dispenser.FiveHundredNotesDispenser;
import atm.version1.dispenser.OneHundredNotesDispenser;
import atm.version1.dispenser.TwoHundredNotesDispenser;
import atm.version2.hardware.ATM;
import atm.version2.hardware.NetworkInfrastructure;
import atm.version2.hardware.NetworkInfrastructureImpl;
import atm.version2.hardware.Printer;
import atm.version2.hardware.PrinterImpl;
import atm.version2.utils.AccountCreationFactory;
import atm.version2.utils.AccountService;

public class Main {
    public static void main(String[] args) {
        // Setup initial accounts
        InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
        AccountCreationFactory accountFactory = new AccountCreationFactory();

        Account currentAccount = accountFactory.create(AccountType.CURRENT, "123456789", 1000.00);
        Account savingsAccount = accountFactory.create(AccountType.SAVINGS, "987654321", 5000.00);

        accountRepository.updateAccount(currentAccount);
        accountRepository.updateAccount(savingsAccount);

        AccountService accountService = new AccountService(accountRepository);

        // Initialize hardware components and network infrastructure
        CardReader cardReader = new CardReaderImpl();
        Keypad keypad = new KeypadImpl();
        Screen screen = new ScreenImpl();
        DepositSlot depositSlot = new DepositSlotImpl();
        Printer printer = new PrinterImpl();
        NetworkInfrastructure networkInfrastructure = new NetworkInfrastructureImpl();

        // Setup cash dispenser chain
        atm.version1.dispenser.CashDispenser fiveHundredDispenser = new FiveHundredNotesDispenser();
        atm.version1.dispenser.CashDispenser twoHundredDispenser = new TwoHundredNotesDispenser();
        CashDispenser oneHundredDispenser = new OneHundredNotesDispenser();

        fiveHundredDispenser.setNextDispenser(twoHundredDispenser);
        twoHundredDispenser.setNextDispenser(oneHundredDispenser);

        // Create ATM instance
        atm.version2.hardware.ATM atm = new ATM(cardReader, keypad, screen, fiveHundredDispenser, depositSlot, printer, networkInfrastructure, accountService);
//        atm.setState(atm.getIdleState());

        // Initialize states

        atm.run();
    }
}

