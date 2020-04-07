/*
 * A linked list implementation of a stack
 */

package com.bhavesh.DataStructures.Stack;

import java.util.LinkedList;
import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<T>();

    // Create an empty stack
    public Stack() {}

    // Create a stack with initial element
    public Stack(T initialElement) {
        push(initialElement);
    }

    // Return number of elements ini the stack
    public int size() {
        return list.size();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Push an element on the stack
    public void push(T element) {
        list.addLast(element);
    }

    // Pop an element off the stack
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return list.removeLast();
    }

    // Peek the top of the stack without removing an element
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return list.peekLast();
    }

    // Allow users to iterate through stack using an iterator
    public Iterator<T> iterator() {
        return list.iterator();
    }

}
