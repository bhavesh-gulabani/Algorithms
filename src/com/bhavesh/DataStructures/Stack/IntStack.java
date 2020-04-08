/*
 * An array implementation of an integer only stack
 */

package com.bhavesh.DataStructures.Stack;

import java.util.EmptyStackException;

public class IntStack {
    private int[] arr;
    private int top;

    // maxSize is the maximum number of items that can be in the stack at any given time
    public IntStack(int maxSize) {
        arr = new int[maxSize];
        top = 0;
    }

    // Returns the number of elements in the stack
    public int size() {
        return top;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Returns the element at the top of the stack
    public int peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return arr[top - 1];
    }

    // Add an element to the top of the stack
    public void push(int value) {
        arr[top++] = value;
    }

    // Pop an element off the stack
    public int pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return arr[--top];
    }
}
