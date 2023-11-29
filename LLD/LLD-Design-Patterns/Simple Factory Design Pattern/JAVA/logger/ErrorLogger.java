package LLD.LLD;

public class ErrorLogger implements ILogger {
    public void log(String msg) {
        System.out.println("ERROR: " + msg);
    }
}
