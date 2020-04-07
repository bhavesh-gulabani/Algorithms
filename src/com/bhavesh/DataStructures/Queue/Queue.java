package com.bhavesh.DataStructures.Queue;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<T>();

    public Queue() {}

    public Queue(T initialElement) {
        enqueue(initialElement);
    }

    // Return the size of the queue
    public int size() {
        return list.size();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Return the element from the front of the queue without removing it
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Queue empty");
        return list.peekFirst();
    }

    // Remove an element from the front of the queue
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue empty");
        return list.removeFirst();
    }

    // Add an element to the back of the queue
    public void enqueue(T element) {
        list.addLast(element);
    }

    // Return an iterator to allow the user to traverse through the elements in the queue
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
