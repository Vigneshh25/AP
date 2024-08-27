package atm.version2.dispenser;

public abstract class CashDispenser {
    protected CashDispenser nextDispenser;

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public void dispense(int amount) {
        if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        }
    }
}
