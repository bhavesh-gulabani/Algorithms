package com.bhavesh.Searching;

import java.util.Scanner;

public class LinearSearch {

    public static int linearSearch(int[] list, int key) {

        for (int i = 0; i < list.length; i++) {
            if (list[i] == key)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] list = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            list[i] = scanner.nextInt();
        }
        int key = scanner.nextInt();

        System.out.println(linearSearch(list, key));
    }


}

