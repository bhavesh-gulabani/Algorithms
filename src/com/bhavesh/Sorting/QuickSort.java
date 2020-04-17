package com.bhavesh.Sorting;

import java.util.Scanner;

public class QuickSort {

    public static void quickSort(int[] array) {
        if (array == null)
            return;
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int splitPoint = partition(array, low, high);
            quickSort(array, low, splitPoint);
            quickSort(array, splitPoint + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int j = low;
        for (int i = low + 1; i < high; i++) {
            if (array[i] <= pivot) {
                j += 1;
                swap(j, i, array);
            }
        }
        swap(low, j, array);
        return j;
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

        quickSort(array);

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print(array[i] + " ");
        }

    }
}
