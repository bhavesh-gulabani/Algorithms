package com.bhavesh.DynamicProgramming;

import java.util.Scanner;

public class LongestCommonSubsequence {

    public static String LCS(char[] A, char[] B) {
        if (A == null || B == null)
            return null;

        final int n = A.length;
        final int m = B.length;

        if (n == 0 || m == 0)
            return null;

        int[][] cache = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // If ends match
                if (A[i - 1] == B[j - 1])
                    cache[i][j] = cache[i - 1][j - 1] + 1;

                // If ends do not match
                else
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
            }
        }

        int lcsLength = cache[n][m];
        char[] lcs = new char[lcsLength];
        int index = 0;

        // Backtrack to find LCS. We search for all the cells
        // where we included an element, which are those satisfying
        // cache[i][j] != cache[i - 1][j] AND cache[i][j] != cache[i][j - 1]
        int i = n, j = m;
        while (i >= 1 && j >= 1) {
            int c = cache[i][j];

            while (i > 1 && cache[i - 1][j] == c) i--;
            while (j > 1 && cache[i][j - 1] == c) j--;

            if (c > 0) {
                lcs[lcsLength - index - 1] = A[i - 1];
                index++;
            }

            i--;
            j--;
        }

        return new String(lcs);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] a = {'A', 'U', 'B', 'C', 'T'};
        char[] b = {'V', 'A', 'W', 'Y', 'B', 'C'};
        System.out.println(LCS(a, b));    // ABC

        a = new char[] {'1', '2', '3', '4', '8', '5', '9', '6', '3', '2', '1'};
        b = new char[] {'2', '1', '4', '3', '7', '4', '6', '9', '3', '1', '4'};
        System.out.println(LCS(a, b));  // 134931
    }
}
