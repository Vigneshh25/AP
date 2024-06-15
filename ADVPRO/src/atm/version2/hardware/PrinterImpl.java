package atm.version2.hardware;

public class PrinterImpl implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
