package com.bhavesh.Sorting;

import java.util.Scanner;

public class CountingSort {

    // Sorts values in the range of [minVal, maxVal] in O(n + maxVal - maxVal)
    public static int[] countingSort(int[] array, int minVal, int maxVal) {
        int size = maxVal - minVal + 1;
        int[] count = new int[size];

        for (int i = 0; i < array.length; i++) {
            count[array[i] - minVal]++;
        }

        int[] sortedArray = new int[array.length];

        for (int i = 0, k = 0; i < size; i++) {
            while (count[i] > 0) {
                sortedArray[k++] = i + minVal;
                count[i]--;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {

        // The maximum and minimum values on the numbers we are sorting.
        // You need to know ahead of time the upper and lower bounds on
        // the numbers you are sorting for counting sort to work.
        final int MIN_VAL = -10;
        final int MAX_VAL = +10;

        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            array[i] = scanner.nextInt();
        }

        array = countingSort(array, MIN_VAL, MAX_VAL);

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print(array[i] + " ");
        }

    }
}
