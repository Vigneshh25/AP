package atm.ServiceLayer;

import atm.InterfaceLayer.DepositSlot;

public class DepositSlotImpl implements DepositSlot {
    @Override
    public void acceptDeposit(double amount) {
        // Simulate accepting deposit
        System.out.println("Accepted deposit of " + amount + " rupees.");
    }
}
