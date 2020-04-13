package com.bhavesh.Greedy;

import java.util.Scanner;

public class CarFueling {

    public static int  getMinimumRefills(int[] stops, int numberOfStops, int maxDistance) {
        int currentRefill = 0;
        int numberOfRefills = 0;
        int lastRefill;

        while (currentRefill <= numberOfStops) {
            lastRefill = currentRefill;
            while ((currentRefill <= numberOfStops) && (stops[currentRefill+1] - stops[lastRefill] <= maxDistance)) {
                currentRefill++;
            }

            if (currentRefill == lastRefill) {
                return -1;
            }

            if (currentRefill <= numberOfStops) {
                numberOfRefills++;
            }
        }
        return numberOfRefills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalDistance = scanner.nextInt();              // The total distance to be travelled
        int maximumDistance = scanner.nextInt();            // Distance travelled with a full tank
        int numberOfStops = scanner.nextInt();              // Number of stops where fuel tank can be refilled
        int[] stops = new int[numberOfStops+2];             // Location of stops
        stops[0] = 0;
        for (int i = 1; i <= numberOfStops; i++) {
            stops[i] = scanner.nextInt();
        }
        stops[numberOfStops+1] = totalDistance;

        System.out.println(getMinimumRefills(stops, numberOfStops, maximumDistance));
    }
}
