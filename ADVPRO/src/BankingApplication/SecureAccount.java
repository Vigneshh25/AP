package BankingApplication;

public class SecureAccount extends Account {
    private String pin;

    public SecureAccount(String accountNumber, double balance, String pin) {
        super(accountNumber, balance, "Secure", "Secure Account");
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean verifyAccess(String enteredPin) {
        return enteredPin.equals(pin);
    }
}
