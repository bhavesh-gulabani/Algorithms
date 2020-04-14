package com.bhavesh.Greedy.FractionalKnapsack;

public class KnapsackPackage {
    private int value;
    private int weight;
    private Double valuePerUnitWeight;

    public KnapsackPackage(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.valuePerUnitWeight = (double) this.value / this.weight;
    }

    public int getValue() {
        return value;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Double getValuePerUnitWeight() {
        return valuePerUnitWeight;
    }
}


