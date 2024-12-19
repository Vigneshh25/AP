package ATM.version2.dispenser;

public class TwoHundredNotesDispenser extends CashDispenser {
    @Override
    public void dispense(int amount) {
        int count = amount / 200;
        int remainder = amount % 200;
        if (count > 0) {
            System.out.println("Dispensing " + count + " 200 notes");
        }
        if (remainder != 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}
