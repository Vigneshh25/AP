package atm.ServiceLayer;


import atm.ModelLayer.CashDispenser;

public class TwoHundredNotesDispenser extends CashDispenser {

    @Override
    public void dispense(int amount) {
        if (amount >= 200) {
            int numNotes = amount / 200;
            int remainder = amount % 200;
            System.out.println("Dispensing " + numNotes + " x 200 rupee notes");
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(remainder);
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        }
    }
}
