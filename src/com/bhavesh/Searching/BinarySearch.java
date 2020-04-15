package com.bhavesh.Searching;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

    // If key is found, returns its index in the sorted list of elements
    // If not found, returns -1
    public static int binarySearch(int[] list, int low, int high, int key) {
        if (high < low)
            return -1;

        int mid = (low + high) / 2;
        if (key == list[mid])
            return mid;
        else if (key < list[mid])
            return binarySearch(list, low, mid - 1, key);
        else
            return binarySearch(list, mid + 1, high, key);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] list = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            list[i] = scanner.nextInt();
        }
        int key = scanner.nextInt();

        // Only works on sorted list of elements
        Arrays.sort(list);

        System.out.println(binarySearch(list, 0, numberOfElements - 1, key));
    }

}
