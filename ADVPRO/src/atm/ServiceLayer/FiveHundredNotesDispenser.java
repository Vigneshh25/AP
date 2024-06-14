package atm.ServiceLayer;


import atm.ModelLayer.CashDispenser;

public class FiveHundredNotesDispenser extends CashDispenser {

    @Override
    public void dispense(int amount) {
        if (amount >= 500) {
            int numNotes = amount / 500;
            int remainder = amount % 500;
            System.out.println("Dispensing " + numNotes + " x 500 rupee notes");
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(remainder);
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        }
    }
}
