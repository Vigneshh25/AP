package atm.version1.devices;

public class CashDispenserImpl implements CashDispenser {
    @Override
    public void dispense(int amount) {
        System.out.println("Dispensing " + amount + " rupees.");
    }
}
