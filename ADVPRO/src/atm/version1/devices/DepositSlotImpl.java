package atm.version1.devices;

public class DepositSlotImpl implements DepositSlot {
    @Override
    public void acceptDeposit(double amount) {
        System.out.println("Accepted deposit of " + amount + " rupees.");
    }
}
