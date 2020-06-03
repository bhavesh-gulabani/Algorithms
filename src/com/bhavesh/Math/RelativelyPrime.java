/*
 * Tests whether two numbers are relatively prime (co-prime)
 * Time complexity: O(log(a + b))
 */

package com.bhavesh.Math;

public class RelativelyPrime {

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static boolean areCoprime(long a, long b) {
        return gcd(a, b) == 1;
    }

    public static void main(String[] args) {
        System.out.println(areCoprime(5, 7));
        System.out.println(areCoprime(20, 32));
    }
}