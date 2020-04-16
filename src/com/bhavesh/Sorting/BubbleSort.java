package com.bhavesh.Sorting;

import java.util.Scanner;

public class BubbleSort {

    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void bubbleSort(int[] array) {
        int numberOfElements = array.length;
        boolean swapped;

        for (int i = 0; i < numberOfElements - 1; i++) {
            swapped = false;
            for (int j = 0; j < (numberOfElements - i - 1); j++) {

                if (array[j] > array[j + 1]) {
                    swap(j, j + 1, array);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] list = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            list[i] = scanner.nextInt();
        }

        bubbleSort(list);

        for (int i = 0; i < numberOfElements; i++) {
            System.out.print(list[i] + " ");
        }
    }
}
