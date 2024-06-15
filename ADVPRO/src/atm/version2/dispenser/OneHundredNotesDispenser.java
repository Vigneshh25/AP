package atm.version2.dispenser;

public class OneHundredNotesDispenser extends CashDispenser {
    @Override
    public void dispense(int amount) {
        int count = amount / 100;
        int remainder = amount % 100;
        if (count > 0) {
            System.out.println("Dispensing " + count + " 100 notes");
        }
        if (remainder != 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}
