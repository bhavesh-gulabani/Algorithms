/*
 * Dynamic programming approach to find the length of the longest increasing subsequence
 * Time complexity: O(n^2)
 */

package com.bhavesh.DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence {

    public static int LIS(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int N = array.length;
        int lisLength = 0;

        // Initially each element has a LIS of exactly one
        int[] cache = new int[N];
        Arrays.fill(cache, 1);


        // Processing the array left to right update the value of cache[j] if two
        // conditions hold 1) The value at i is less than that of the one at j
        // and 2) updating the value of cache[j] to cache[i] + 1 is better
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (array[i] < array[j] && cache[j] < cache[i] + 1) {
                    cache[j] = cache[i] + 1;
                }
            }

            if (cache[i] > lisLength)
                lisLength = cache[i];
        }

        return lisLength;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = scanner.nextInt();

        System.out.println(LIS(array));
    }
}
