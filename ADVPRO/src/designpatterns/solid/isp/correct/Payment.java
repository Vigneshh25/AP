package designpatterns.solid.isp.correct;

import java.util.List;

/*
 * interface segregation simply means that we should break larger interfaces into smaller ones.
 * Clients should not be forced to depend upon interfaces that they do not use
 *
 * Clients should not be forced to depend upon interfaces that they do not use.
 * it would be bad for you to force the client to depend on a certain thing, which they don’t need.
 * */
public interface Payment {
    Object status();
    List<Object> getPayments();
}

interface Bank extends Payment {
    void initiatePayments();
}

interface Loan extends Payment {
    void intiateLoanSettlement();
    void initiateRePayment();
}

class BankPayment implements Bank {

    @Override
    public void initiatePayments() {
        // ...
    }

    @Override
    public Object status() {
        return null;
    }

    @Override
    public List<Object> getPayments() {
        return null;
    }
}

class LoanPayment implements Loan {

    @Override
    public void intiateLoanSettlement() {
        //           return null;
    }

    @Override
    public void initiateRePayment() {
        // ...
    }

    @Override
    public Object status() {
        return null;
    }

    @Override
    public List<Object> getPayments() {
        return null;
    }
}