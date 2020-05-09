/*
 * Dynamic programming solution to the classic 0/1 knapsack problem
 * where we are trying to maximize the total profit of items selected
 * without exceeding the capacity of our knapsack
 *
 * Time complexity: O(nW)
 * Space complexity: O(nW)
 */

package com.bhavesh.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack01 {

    public static int knapsack(int capacity, int[] weights, int[] values) {

        if (weights == null || values == null || weights.length != values.length || capacity < 0)
            throw new IllegalArgumentException("Invalid input");

        final int N = weights.length;

        // Initialize a table with rows representing items and
        // columns representing weight of the knapsack
        int[][] cache = new int[N + 1][capacity + 1];

        for (int i = 1; i <= N; i++) {

            // Get the value and weight of the item
            int w = weights[i - 1], v = values[i - 1];

            for (int size = 1; size <= capacity; size++) {

                // Consider not picking this element
                cache[i][size] = cache[i - 1][size];

                // Consider including the current element and check
                // if this would be more profitable
                if ((size >= w) && (cache[i - 1][size - w] + v > cache[i][size]))
                    cache[i][size] = cache[i - 1][size - w] + v;
            }
        }

        int size = capacity;
        List<Integer> itemsSelected = new ArrayList<>();

        // Using the information inside the table we can backtrack and determine
        // which items were selected. The idea is that if cache[i][size] != cache[i-1][size]
        // then the item was selected
        for (int i = N; i > 0; i--) {
            if (cache[i][size] != cache[i - 1][size]) {
                int itemIndex = i - 1;
                itemsSelected.add(itemIndex);
                size -= weights[itemIndex];
            }
        }

        // Return the items that were selected
        // java.util.Collections.reverse(itemsSelected);
        // return itemsSelected;

        // Return the maximum profit
        return cache[N][capacity];
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

        System.out.println(knapsack(capacity, weights, values));
    }
}