/*
 * A generic dynamic array implementation
 */

package com.bhavesh.DataStructures.DynamicArray;

public class DynamicArray<T> implements Iterable<T>{
    private T[] array;
    private int length = 0;     // Size of array for user
    private int capacity = 0;   // Actual array size

    public DynamicArray(){
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T access(int index) {
        if (index >= length || index < 0) throw new IndexOutOfBoundsException();
        return array[index];
    }

    public void update(int index, T element) {
        if (index >= length || index < 0) throw new IndexOutOfBoundsException();
        array[index] = element;
    }

    public void clear() {
        for(int i = 0; i < length; i++) {
            array[i] = null;
        }
        length = 0;
    }

    public void append(T element) {
        // Condition for resize
        if (length + 1 > capacity) {
            if (capacity == 0)
                capacity = 1;
            else
                capacity *= 2;          // Double the size

            T[] new_array = (T[]) new Object[capacity];
            for(int i = 0; i < length; i++) {
                new_array[i] = array[i];
            }
            array = new_array;          // array has extra nulls padded
        }

        array[length++] = element;
    }

    public T removeAt(int remIndex) {
        if (remIndex >= length || remIndex < 0) throw new IndexOutOfBoundsException();

        T remElement = array[remIndex];
        for(int i = remIndex+1; i < length; i++) {
            array[i-1] = array[i];
        }

        array[length - 1] = null;
        capacity = --length;

        return remElement;
    }

    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1)
            return false;

        removeAt(index);
        return true;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < length; i++) {
            if (obj == null) {
                if (array[i] == null)
                    return i;
            } else {
                    if (obj.equals(array[i]))
                        return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (length == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(length).append("[");
            for (int i = 0; i < length - 1; i++) sb.append(array[i] + ", ");
            return sb.append(array[length - 1] + "]").toString();
        }
    }

}