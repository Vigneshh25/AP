package Zkart;

class Solution {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int[] count = {3, 2, 1};
        System.out.println(coinChange(coins.length - 1, 5, coins, count));
    }


    static int coinChange(int ind, int amount, int[] coins, int[] count) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;
        if (ind < 0) return 0;

        int sum = 0;
        sum += coinChange(ind - 1, amount, coins, count);
        if (amount >= coins[ind] && count[ind] > 0) {
            count[ind] -= 1;
            sum += coinChange(ind, amount - coins[ind], coins, count);
            count[ind] += 1;
        }
        return sum;
    }
}