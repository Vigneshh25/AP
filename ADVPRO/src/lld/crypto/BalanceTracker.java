package lld.crypto;

class BalanceTracker implements BalanceObserver {
    private User user;

    public BalanceTracker(User user) {
        this.user = user;
    }

    @Override
    public void update(String walletId) {
        System.out.println("Account balances updated for user " + user.getUsername() + ", wallet: " + walletId);
    }
}
