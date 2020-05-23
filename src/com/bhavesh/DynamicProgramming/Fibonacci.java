/*
 * A dynamic programming approach to find the nth fibonacci number
 * Time complexity: O(n)
 */

package com.bhavesh.DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

public class Fibonacci {

    public static int fiboMemoization(int n) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return fiboMemoization(n, cache);
    }

    // Memoized DP algorithm
    private static int fiboMemoization(int n, HashMap<Integer, Integer> cache) {
        int Fn;

        if (cache.containsKey(n)) return cache.get(n);
        if (n == 0) Fn = 0;
        else if (n == 1) Fn = 1;
        else
            Fn = fiboMemoization(n - 1, cache) + fiboMemoization(n - 2, cache);
        cache.put(n, Fn);
        return Fn;
    }

    // Bottom-up DP algorithm
    private static int fiboBottomUp(int n) {

        int[] cache = new int[n + 1];
        cache[0] = 0;

        int Fi;
        for (int i = 1; i <= n; i++) {
            if (i == 1)
                Fi = 1;
            else
                Fi = cache[i - 1] + cache[i - 2];
            cache[i] = Fi;
        }
        return cache[n];
    }

    // Space efficient bottom-up DP algorithm
    private static int fiboBottomUpSpaceEfficient(int n) {
        if (n == 0) return  0;
        else if (n == 1) return 1;

        int Fi = 0, Fj = 1, Fk = 1;
        for (int k = 2; k <= n; k++) {
            Fk = Fi + Fj;
            Fi = Fj;
            Fj = Fk;
        }
        return Fk;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(fiboMemoization(n));
        // System.out.println(fiboBottomUp(n));
        // System.out.println(fiboBottomUpSpaceEfficient(n));
    }
}