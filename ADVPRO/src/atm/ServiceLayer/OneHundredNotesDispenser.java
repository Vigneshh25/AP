package atm.ServiceLayer;


import atm.ModelLayer.CashDispenser;

public class OneHundredNotesDispenser extends CashDispenser {

    @Override
    public void dispense(int amount) {
        if (amount >= 100) {
            int numNotes = amount / 100;
            int remainder = amount % 100;
            System.out.println("Dispensing " + numNotes + " x 100 rupee notes");
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(remainder);
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        }
    }
}
