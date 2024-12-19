package ATM.version2.states;

public interface ATMState {
    void insertCard();
    void ejectCard();
    void enterPIN(String pin);
    void requestOperation();
}
