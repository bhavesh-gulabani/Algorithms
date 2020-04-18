package com.bhavesh.Sorting;

import java.util.Scanner;

public class InsertionSort {

    public static void insertionSort(int[] array) {
        if (array == null)
            return;

        int n = array.length;

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(j - 1, j, array);
            }
        }
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            array[i] = scanner.nextInt();
        }

        insertionSort(array);

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print(array[i] + " ");
        }

    }
}