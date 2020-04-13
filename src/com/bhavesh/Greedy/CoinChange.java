package com.bhavesh.Greedy;

import java.util.Scanner;

public class CoinChange {

    private static int getMinimumCoins(int amount) {
        int[] denominations = {10, 5, 1};
        int numberOfCoins = 0;
        int i = 0;

        while (amount > 0) {
            while (amount >= denominations[i]) {
                amount -= denominations[i];
                numberOfCoins++;
            }
            i++;
        }
        return numberOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        System.out.println(getMinimumCoins(amount));
    }
}
