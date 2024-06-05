package lld.crypto;

public class CryptoExchangePlatform {
    public static void main(String[] args) {
        // Create users
        User user1 = UserFactory.createUser("user1", "John", "password1");
        User user2 = UserFactory.createUser("user2", "Alice", "password2");

        // Create wallets
        Wallet wallet1 = WalletFactory.createWallet("wallet1", user1);
        Wallet wallet2 = WalletFactory.createWallet("wallet2", user2);

        // Create balance trackers
        BalanceTracker balanceTracker1 = new BalanceTracker(user1);
        BalanceTracker balanceTracker2 = new BalanceTracker(user2);

        // Subscribe balance trackers to wallets
        wallet1.subscribeObserver(balanceTracker1);
        wallet2.subscribeObserver(balanceTracker2);

        // Deposit funds into wallet1
        wallet1.deposit("BTC", 1.5);

        // Withdraw funds from wallet2
        try {
            wallet2.withdraw("ETH", 2.0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Print balances
        System.out.println("Wallet1 balances: " + wallet1.getBalances());
        System.out.println("Wallet2 balances: " + wallet2.getBalances());
    }
}
