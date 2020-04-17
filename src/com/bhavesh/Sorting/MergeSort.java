package com.bhavesh.Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    public static int[] mergeSort(int[] array) {

        int n = array.length;
        if (n == 1)
            return array;

        int mid = n / 2;

        int[] left = mergeSort(Arrays.copyOfRange(array, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(array, mid, n));

        return merge(left, right);
    }

    private static int[] merge(int[] array1, int[] array2) {
        int n1 = array1.length;
        int n2 = array2.length;

        int n = n1 + n2;
        int[] mergedArray = new int[n];

        int i1 = 0, i2 = 0;

        for (int i = 0; i < n; i++) {
            if (i1 == n1) {
                mergedArray[i] = array2[i2++];
            }
            else if (i2 == n2) {
                mergedArray[i] = array1[i1++];
            }
            else {
                if (array1[i1] < array2[i2]) {
                    mergedArray[i] = array1[i1++];
                }
                else {
                    mergedArray[i] = array2[i2++];
                }
            }
        }
        return mergedArray;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            array[i] = scanner.nextInt();
        }

        array = mergeSort(array);

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print(array[i] + " ");
        }

    }

}
