package com.bhavesh.Greedy.FractionalKnapsack;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class FractionalKnapsack {

    public static double getMaximumValue(int[] values, int[] weights, int capacity) {
        int numberOfItems = values.length;

        KnapsackPackage[] packages = new KnapsackPackage[numberOfItems];
        for (int i = 0;  i < numberOfItems;  i++) {
            packages[i] = new KnapsackPackage(values[i], weights[i]);
        }

        Arrays.sort(packages, new Comparator<KnapsackPackage>() {
            @Override
            public int compare(KnapsackPackage packageA, KnapsackPackage packageB) {
                return packageB.getValuePerUnitWeight().compareTo(packageA.getValuePerUnitWeight());
            }
        });

        double[] selected = new double[numberOfItems];

        double maximumValue = 0;
        int partSelected = 0;

        for (int i = 0; i < numberOfItems; i++) {

            if (capacity == 0)
                return maximumValue;

            partSelected = Integer.min(packages[i].getWeight(), capacity);
            maximumValue += partSelected * packages[i].getValuePerUnitWeight();

            packages[i].setWeight(packages[i].getWeight() - partSelected);
            selected[i] = selected[i] + partSelected;
            capacity = capacity - partSelected;
        }
        return maximumValue;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfItems = scanner.nextInt();
        int capacity = scanner.nextInt();
        int values[] = new int[numberOfItems];
        int weights[] = new int[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }

        System.out.println(getMaximumValue(values, weights, capacity));
    }
}
