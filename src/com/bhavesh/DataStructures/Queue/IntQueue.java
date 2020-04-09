/*
 * An array implementation of an integer only queue
 */

package com.bhavesh.DataStructures.Queue;

public class IntQueue {
    private int[] arr;
    private int front, rear, size;

    // maxSize is the maximum number of items that can be in the queue at any given time
    public IntQueue(int maxSize) {
        front = rear = 0;
        size = maxSize + 1;
        arr = new int[size];
    }

    // Check whether the queue is empty
    public boolean isEmpty() {
        return front == rear;
    }

    // Return the number of elements in the queue
    public int size() {
        if (front > rear)
            return (rear + size - front);
        return rear - front;
    }

    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Queue empty");
        return arr[front];
    }

    // Add an element to the queue
    public void enqueue(int value) {
        arr[rear] = value;

        if (++rear == size)
            rear = 0;

        if (rear == front)
            throw new RuntimeException("Queue too small");
    }

    // Remove an element from the front of the queue
    public int dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue empty");
        int val = arr[front];
        if (++front == size)
            front = 0;

        return val;
    }
}
