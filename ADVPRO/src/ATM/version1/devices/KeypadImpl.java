package ATM.version1.devices;

import java.util.Scanner;

public class KeypadImpl implements Keypad {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String enterPIN() {
        System.out.print("Enter PIN: ");
        String pin = scanner.next();
        return pin;
    }

    @Override
    public double enterAmount() {
        System.out.print("Enter amount: ");
        return scanner.nextDouble();
    }
}
