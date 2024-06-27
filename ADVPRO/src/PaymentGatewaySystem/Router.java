package PaymentGatewaySystem;

import PaymentGatewaySystem.bank.Bank;
import PaymentGatewaySystem.bank.PaymentMode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Router Class
class Router {
    private Map<PaymentMode, List<Bank>> bankDistribution;
    private Map<Bank, AtomicInteger> bankSuccessCount;

    public Router() {
        this.bankDistribution = new HashMap<>();
        this.bankSuccessCount = new HashMap<>();
    }

    public void addBank(PaymentMode mode, Bank bank) {
        bankDistribution.computeIfAbsent(mode, k -> new ArrayList<>()).add(bank);
        bankSuccessCount.putIfAbsent(bank, new AtomicInteger(0));
    }

    public Bank getBank(PaymentMode mode) {
        List<Bank> banks = bankDistribution.get(mode);
        if (banks == null || banks.isEmpty()) {
            throw new IllegalStateException("No banks available for payment mode: " + mode);
        }
        // Simple round-robin distribution
        Bank selectedBank = banks.get(new Random().nextInt(banks.size()));
        return selectedBank;
    }

    public void recordSuccess(Bank bank) {
        bankSuccessCount.get(bank).incrementAndGet();
    }

    public void showDistribution() {
        for (Map.Entry<Bank, AtomicInteger> entry : bankSuccessCount.entrySet()) {
            System.out.println(entry.getKey().getClass().getSimpleName() + ": " + entry.getValue().get() + " successful transactions");
        }
    }
}
