package lld.crypto;

class UserFactory {
    public static User createUser(String userId, String username, String password) {
        return new User(userId, username, password);
    }
}

class WalletFactory {
    public static Wallet createWallet(String walletId, User user) {
        return new Wallet(walletId, user);
    }
}

class OrderFactory {
    public static Order createOrder(String orderId, User user, String tradingPair, String type, double price, double quantity) {
        return new Order(orderId, user, tradingPair, type, price, quantity);
    }
}
