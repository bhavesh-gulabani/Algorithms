package com.bhavesh.Sorting;

import java.util.Scanner;

public class SelectionSort {


    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void selectionSort(int[] array) {
        int minIndex;
        int numberOfElements = array.length;

        for (int i = 0; i < numberOfElements; i++) {
            minIndex = i;
            for (int j = i + 1; j < numberOfElements; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(i, minIndex, array);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            array[i] = scanner.nextInt();
        }

        selectionSort(array);

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
