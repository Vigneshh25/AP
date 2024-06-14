package atm;


import atm.InterfaceLayer.*;
import atm.ModelLayer.*;
import atm.RepositoryLayer.InMemoryAccountRepository;
import atm.ServiceLayer.*;
import atm.StateLayer.IdleState;

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
        CashDispenser fiveHundredDispenser = new FiveHundredNotesDispenser();
        CashDispenser twoHundredDispenser = new TwoHundredNotesDispenser();
        CashDispenser oneHundredDispenser = new OneHundredNotesDispenser();

        fiveHundredDispenser.setNextDispenser(twoHundredDispenser);
        twoHundredDispenser.setNextDispenser(oneHundredDispenser);

        // Create ATM instance
        ATM atm = new ATM(cardReader, keypad, screen, fiveHundredDispenser, depositSlot, printer, networkInfrastructure, accountService);
        atm.setCurrentState(new IdleState(atm));
        atm.run();
    }
}
