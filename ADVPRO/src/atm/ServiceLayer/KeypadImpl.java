package atm.ServiceLayer;

import atm.InterfaceLayer.Keypad;

import java.util.Scanner;

public class KeypadImpl implements Keypad {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String enterPIN() {
        System.out.print("Enter PIN: ");
        return scanner.nextLine();
    }

    @Override
    public double enterAmount() {
        System.out.print("Enter amount: ");
        return scanner.nextDouble();
    }
}
