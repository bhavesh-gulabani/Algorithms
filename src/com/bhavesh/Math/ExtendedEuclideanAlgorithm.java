/*
 * An implementation of Extended Euclidean algorithm
 * Time complexity: O(log(a + b))
 */
package com.bhavesh.Math;

public class ExtendedEuclideanAlgorithm {

    // This function returns the gcd(a,b) as well as
    // the numbers x and y such that ax + by = gcd(a,b)
    public static long[] egcd(long a, long b) {
        if (b == 0) return new long[] {a, 1, 0};
        else {
            long[] ans = egcd(b, a % b);
            long temp = ans[1] - ans[2] * (a / b);
            ans[1] = ans[2];
            ans[2] = temp;
            return ans;
        }
    }

    public static void main(String[] args) {
        long[] ans = egcd(55, 80);
        System.out.println("GCD = " + ans[0]);
        System.out.println("x = " + ans[1] + " y = " + ans[2]);
    }
}