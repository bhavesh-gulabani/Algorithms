/*
 * Test whether a number is prime or not
 * Time complexity: O(sqrt(n))
 */

package com.bhavesh.Math;

public class IsPrime {

    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        long limit = (long) Math.sqrt(n);

        for (int i = 5; i <= limit; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(5));
        System.out.println(isPrime(240));
        System.out.println(isPrime(503));
    }
}