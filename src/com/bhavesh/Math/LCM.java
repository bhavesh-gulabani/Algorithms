/*
 * An implementation of finding the LCM of two numbers
 * Time complexity: O(log(a + b))
 */

package com.bhavesh.Math;

public class LCM {

    private static long gcd(long a, long b) {
        return b == 0 ? (a < 0 ? -a : a) : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        long lcm = (a * b) / gcd(a, b);
        return lcm > 0 ? lcm : -lcm;
    }

    public static void main(String[] args) {
        System.out.println(lcm(24, 18));    // 72
        System.out.println(lcm(-24, 18));   // 72
        System.out.println(lcm(40, 25));    // 200
        System.out.println(lcm(120, 20));   // 120

    }
}