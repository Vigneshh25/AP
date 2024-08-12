package Design_Datastructure.java8.concurrency.patterns;

import java.math.BigInteger;

/**
 * Pattern: Fixed Lock Ordering
 *
 * Example: Simulating a coin transfer between players
 */
public class CoinTransfer {

    public static void main(String[] args) {
        CoinTransfer coinTransfer = new CoinTransfer();
        Player srcPlayer = new Player();
        srcPlayer.setId(1);
        srcPlayer.setName("SrcPlayer");
        srcPlayer.setCoins(new BigInteger("1000"));

        Player destPlayer = new Player();
        destPlayer.setId(2);
        destPlayer.setName("DestPlayer");
        destPlayer.setCoins(new BigInteger("900"));

        coinTransfer.transferBetweenPlayers(srcPlayer,destPlayer,new BigInteger("850"));
    }

    public void transferBetweenPlayers(Player playerFrom, Player playerTo, BigInteger amount) {
        int from = playerFrom.getId();
        int to = playerTo.getId();
        if (from < to) {
            synchronized (playerFrom) {
                synchronized (playerTo) {
                    transferLogic(playerFrom, playerTo, amount);
                }
            }
        } else {
            synchronized (playerTo) {
                synchronized (playerFrom) {
                    transferLogic(playerFrom, playerTo, amount);
                }
            }
        }
        System.out.println("-------------------------------");
        System.out.print("Source: ");
        System.out.println(playerFrom.toString());
        System.out.print("Destination: ");
        System.out.println(playerTo.toString());
        System.out.println("-------------------------------");
    }

    public void transferLogic(Player playerFrom, Player playerTo, BigInteger amount) {
        playerFrom.withdrawCoins(amount);
        playerTo.depositCoins(amount);
    }

    static class Player {
        private int id;
        private String name;
        private BigInteger coins;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coins=" + coins +
                    '}';
        }

        public void setCoins(BigInteger coins) {
            this.coins = coins;
        }

        public void depositCoins(BigInteger amount) {
            if (amount.intValue() < -1)
                throw new IllegalArgumentException("Amount can't be negative");
            this.coins = this.coins.add(amount);
        }

        public void withdrawCoins(BigInteger amount) {
            if (amount.intValue() < -1)
                throw new IllegalArgumentException("Amount can't be negative");
            this.coins = this.coins.subtract(amount);
        }
    }

}