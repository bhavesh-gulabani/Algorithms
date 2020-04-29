package com.bhavesh.DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {

    private static final int INF = 987654321;

    public static int coinChange(int[] coins, int amount) {
        if (coins == null)
            throw new IllegalArgumentException("Coins array is null");

        final int N = coins.length;

        // Initialize table and set first row to infinity
        int[][] cache = new int[N + 1][amount + 1];
        Arrays.fill(cache[0], INF);
        cache[1][0] = 0;

        for (int i = 1; i <= N; i++) {
            int coinValue = coins[i - 1];
            for(int j = 1; j <= amount; j++) {

                // Consider not selecting this coin
                cache[i][j] = cache[i - 1][j];

                // Select this coin if it is better
                if (j - coinValue >= 0 && cache[i][j - coinValue] + 1 < cache[i][j]) {
                    cache[i][j] = cache[i][j - coinValue] + 1;
                }
            }
        }

        // The change cannot be made for the amount
        if (cache[N][amount] == INF)
            return -1;

        return cache[N][amount];
    }

    public static int coinChangeSpaceEfficient(int[] coins, int amount) {
        if (coins == null)
            throw new IllegalArgumentException("Coins array is null");

        // Initialize table and set everything to infinity except first cell
        int[] cache = new int[amount + 1];
        Arrays.fill(cache, INF);
        cache[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coinValue : coins) {
                if (i - coinValue >= 0 && cache[i - coinValue] + 1 < cache[i]) {
                    cache[i] = cache[i - coinValue] + 1;
                }
            }
        }

        // The change cannot be made for the amount
        if (cache[amount] == INF)
            return -1;

        return cache[amount];
    }


    // The recursive approach has the advantage that it does not visit all
    // the possible states like the tabular approach
    // This can speedup things especially if the coin denominations are large
    public static int coinChangeRecursive(int[] coins, int amount) {
        if (coins == null) throw new IllegalArgumentException("Coins array is null");
        if (amount < 0) return -1;

        int[] cache = new int[amount + 1];
        return coinChangeRecursive(amount, coins, cache);
    }

    private static int coinChangeRecursive(int amount, int[] coins, int[] cache) {

        // Base cases
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (cache[amount] != 0) return cache[amount];

        int minCoins = INF;
        for (int coinValue : coins) {
            int newAmount = amount - coinValue;
            int value = coinChangeRecursive(newAmount,coins, cache);
            if (value != -1 && value < minCoins)
                minCoins = value + 1;
        }

        cache[amount] = (minCoins == INF) ? -1 : minCoins;
        return cache[amount];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coins = {10, 5, 1};
        int amount = scanner.nextInt();

        System.out.println(coinChange(coins, amount));
        System.out.println(coinChangeSpaceEfficient(coins, amount));
        System.out.println(coinChangeRecursive(coins, amount));

    }
}
