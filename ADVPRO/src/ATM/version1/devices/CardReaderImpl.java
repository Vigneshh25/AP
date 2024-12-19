package ATM.version1.devices;

import java.util.Scanner;

public class CardReaderImpl implements CardReader {
    @Override
    public String readCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        return scanner.next();
    }
}
