/*
 * A singly linked list implementation
 */

package com.bhavesh.DataStructures.LinkedList;

public class SinglyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // Internal Node class to represent data
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty this linked list, O(n)
    public void clear() {
        Node<T> trav = head;
        while(trav != null) {
            Node<T> next = trav.next;
            trav.next = null;
            trav.data = null;
            trav = next;
        }

        head = tail = trav = null;
        size = 0;
    }

    // Return the size of this linked list
    public int size() {
        return size;
    }

    // Is linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Append an element to the linked list
    public void append(T element) {
        addLast(element);
    }

    // Add a node to the end of the linked list, O(1)
    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null);
        }
        else {
            tail.next = new Node<T>(element, null);
            tail = tail.next;
        }
        size++;
    }

    // Add a node to the beginning of the linked list, O(1)
    public void addFirst(T element) {
        if(isEmpty()) {
            head = tail = new Node<T>(element, null);
        }
        else {
            Node<T> trav = new Node(element, head);
            head = trav;
        }
        size++;
    }

    // Add an element at a specified index
    public void addAt(int index, T data) throws Exception {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal Index");
        }
        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        Node<T> newNode = new Node(data, temp.next);
        temp.next = newNode;

        size++;
    }

    // Check value of first node if it exists, O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Check value of last node if it exists, O(1)
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // Remove the first value at the head of the linked list O(1)
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Store the head reference for de-allocating memory
        Node<T> temp = head;

        // Extract data from the head and move the head pointer forward by one node
        T data = head.data;
        head = head.next;
        --size;

        // If the list is empty, set the tail to null
        if (isEmpty())
            tail = null;

        // Do a memory clean up of the previous node
        else
            temp = null;

        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove the last value at the tail of the linked list O(n)
    public T removeLast() {
        // Check if list is empty
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract data at the tail
        T data = tail.data;

        // Traverse the list till we find tail
        Node<T> trav = head;

        while (trav.next != tail)
            trav = trav.next;

        trav.next = null;

        tail = trav;
        --size;

        if (isEmpty())
            head = null;

        return data;
    }

    // Remove an arbitrary node from the linked list O(n)
    private T remove(Node<T> node) {
        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node == head) return removeFirst();
        if (node == tail) return removeLast();

        Node<T> trav = head;
        while (trav.next != node)
            trav = trav.next;

        trav.next = node.next;

        T data = node.data;

        node.data = null;
        node = node.next = null;

        --size;

        return data;
    }

    // Remove a node at a particular index O(n)
    public T removeAt(int index) {
        // Make sure index provided is vaild
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();

        int i;
        Node<T> trav;

        for (i = 0, trav = head; i != index; i++) {
            trav = trav.next;
        }

        return remove(trav);
    }

    // Remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        }
        else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null)
                    return index;
            }
        }
        else {
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data))
                    return index;
            }
        }
        return -1;
    }

    // Check of a value is contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) {
                sb.append(", ");
            }
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
