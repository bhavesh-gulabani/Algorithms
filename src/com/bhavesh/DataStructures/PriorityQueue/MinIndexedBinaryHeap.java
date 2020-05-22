/*
 * An implementation of an indexed binary hep priority queue
 */

package com.bhavesh.DataStructures.PriorityQueue;

public class MinIndexedBinaryHeap<T extends Comparable<T>> extends MinIndexedDHeap<T> {
    public MinIndexedBinaryHeap(int maxSize) {
        super(2, maxSize);
    }
}
