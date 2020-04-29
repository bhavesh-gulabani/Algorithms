/*
 * An implementation of the edit distance algorithm
 * Time complexity: O(nm)
 */

package com.bhavesh.DynamicProgramming;
import java.util.Scanner;

public class EditDistance {

    // Computes the cost to convert a string 'a' into a string 'b' using dynamic
    // programming assuming the insertion cost, deletion cost and substitution cost to be 1
    public static int editDistance(String a, String b) {
        final int AL = a.length(), BL = b.length();
        int[][] cache = new int[AL + 1][BL + 1];

        for (int i = 0; i <= AL; i++) {
            cache[i][0] = i;
        }

        for (int j = 0; j <= BL; j++) {
            cache[0][j] = j;
        }

        for (int i = 1; i <= AL; i++) {
            for (int j = 1; j <= BL; j++) {
                int min = Integer.MAX_VALUE;

                // Substitution
                min = cache[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1);

                // Insertion
                min = Math.min(min, cache[i][j - 1] + 1);

                // Deletion
                min = Math.min(min, cache[i - 1][j] + 1);

                cache[i][j] = min;
            }
        }
        return cache[AL][BL];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        System.out.println(editDistance(a, b));

    }
}
