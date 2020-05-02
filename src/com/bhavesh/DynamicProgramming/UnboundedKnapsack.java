/*
 * Dynamic programming solution to the classic unbounded knapsack problem
 * where we are trying to maximize the total profit of items selected
 * without exceeding the capacity of our knapsack
 *
 * Time complexity: O(nW)
 * Space complexity:
 *      Version 1:  O(nW)
 *      Version 2:  O(W)
 */

package com.bhavesh.DynamicProgramming;

import java.util.Scanner;

public class UnboundedKnapsack {

    public static int unboundedKnapsack(int capacity, int[] weights, int[] values) {
        
        if (weights == null || values == null || weights.length != values.length || capacity < 0)
            throw new IllegalArgumentException("Invalid input");
        
        final int N = weights.length;
        
        // Initialize a table with rows representing items and
        // columns representing weight of the knapsack
        int[][] cache = new int[N + 1][capacity + 1];
        
        for (int i = 1; i <= N; i++) {
            
            // Get the value and weight of the item
            int w = weights[i - 1], v = values[i - 1];
            
            // Consider all possible knapsack sizes
            for (int size = 1; size <= capacity; size++) {
                
                // Try including the current element
                if (size >= w)
                    cache[i][size] = cache[i][size - w] + v;
                
                // Check if not selecting this item is more profitable
                if (cache[i - 1][size] > cache[i][size])
                    cache[i][size] = cache[i - 1][size];
                
            }
        }

        return cache[N][capacity];
    }


    public static int unboundedKnapsackSpaceEfficient(int capacity, int[] weights, int[] values) {
        if (weights == null || values == null || weights.length != values.length || capacity < 0)
            throw new IllegalArgumentException("Invalid input");

        final int N = weights.length;

        // Initialize a table which will only keep track of the
        // best possible value for every knapsack weight
        int[] cache = new int[capacity + 1];

        for (int size = 1; size <= capacity; size++) {

            for (int i = 0; i < N; i++) {

                // Check if we can include this item, assuming it fits inside the
                // knapsack check if including this item would be profitable
                if (size >= weights[i] && cache[size - weights[i]] + values[i] > cache[size])
                    cache[size] = cache[size - weights[i]] + values[i];

            }
        }

        return cache[capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfItems = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[numberOfItems];
        int[] weights = new int[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            weights[i] = scanner.nextInt();
        }

        for (int i = 0; i < numberOfItems; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.println(unboundedKnapsackSpaceEfficient(capacity, weights, values));
    }
}
