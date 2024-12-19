package ATM.version2.dispenser;

public class FiveHundredNotesDispenser extends CashDispenser {
    @Override
    public void dispense(int amount) {
        int count = amount / 500;
        int remainder = amount % 500;
        if (count > 0) {
            System.out.println("Dispensing " + count + " 500 notes");
        }
        if (remainder != 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}
