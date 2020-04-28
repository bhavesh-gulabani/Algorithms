/*
 * Implementation of a Binary Search Tree. Any comparable data is allowed
 *  within this tree (numbers, strings, comparable Objects, etc..)
 */
package com.bhavesh.DataStructures.BinarySearchTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree <T extends Comparable<T>> {

    // To keep track of number of nodes in the BST
    private int nodeCount = 0;

    private Node root = null;

    // Internal Node containing node references and the actual node data
    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T element) {
            this.data = element;
            this.left = left;
            this.right = right;
        }
    }

    // Check if the binary tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Return the number of nodes in the tree
    public int size() {
        return nodeCount;
    }

    // Add an element to this tree. Returns true
    // if we successfully perform an insertion
    public boolean add(T element) {

        // Check if value already exists, if it does ignore adding it
        if (contains(element)) {
            return false;
        }
        else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    // Private method to recursively add a value in the binary tree
    private Node add(Node node, T element) {

        // Base case: Found a leaf node
        if (node == null) {
            node = new Node(null, null, element);
        }
        else {

            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return node;
    }

    // Remove a value from this tree if it exists
    public boolean remove(T element) {

        // Check if the node we want to remove exists in the tree
        if (contains(element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T element) {
        if (node == null)
            return null;

        int compareValue = element.compareTo(node.data);

        // Dig into the left subtree
        if (compareValue < 0) {
            node.left = remove(node.left, element);
        }
        // Dig into the right subtree
        else if (compareValue > 0) {
            node.right = remove(node.right, element);
        }
        // Found the node we wish to remove
        else {

            // Case with only a right subtree or no subtree at all
            // We just swap the node we wish to remove with its right child
            if (node.left == null) {
                Node rightChild = node.right;

                node.data = null;
                node = null;

                return rightChild;
            }

            // Case with only a left subtree or no subtree at all
            // We just swap the node we wish to remove with its left child
            else if (node.right == null) {
                Node leftChild = node.left;

                node.data = null;
                node = null;

                return leftChild;
            }

            // Case with both subtrees
            // The successor node can be the largest value in the left subtree or
            // the smallest value in the right subtree
            // In this implementation I have decided to find the smallest value in the right subtree
            else {

                // Find the leftmost node in the right subtree
                Node temp = findMin(node.right);

                // Swap this data
                node.data = temp.data;

                // Go to the right subtree and remove the leftmost node we
                // found and swapped data with
                node.right = remove(node.right, temp.data);

                // If instead we wanted to find the largest node in the left
                // subtree as opposed to smallest node in the right subtree :
                // Node temp = findMax(node.left);
                // node.data = temp.data;
                // node.left = remove(node.left, temp.data);
            }
        }
        return node;
    }

    // Helper method to find the leftmost node
    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Helper method to find the rightmost node
    private Node findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    // Check for containment
    public boolean contains(T element) {
        return contains(root, element);
    }

    // Private recursive method to find an element in the tree
    private boolean contains(Node node, T element) {

        // Base case: Reached bottom, value not found
        if (node == null)
            return false;

        int compareValue = element.compareTo(node.data);

        // Dig into the left subtree because the value we're
        // looking for is smaller than the current value
        if (compareValue < 0)
            return contains(node.left, element);

        // Dig into the right subtree because the value we're
        // looking for is greater than the current value
        else if (compareValue > 0)
            return contains(node.right, element);

        // Found the value
        else
            return true;
    }

    // Computes the height of the tree
    public int height() {
        return height(root);
    }

    // Recursive helper method to compute the height of the tree
    private int height(Node node) {
        if (node == null)
            return 0;

        return Math.max(height(node.left), height(node.right)) + 1;
    }


    // Returns an iterator for a given TreeTraversalOrder
    public void traverse(TreeTraversalOrder order) {
        switch(order) {
            case PRE_ORDER:
                preOrderTraversal(root);
                break;
            case IN_ORDER:
                inOrderTraversal(root);
                break;
            case POST_ORDER:
                postOrderTraversal(root);
                break;
            case LEVEL_ORDER:
                levelOrderTraversal(root);
                break;
        }
    }

    private void preOrderTraversal(Node node) {
        if (node == null)
            return;

        System.out.println(node.data);
        if (node.left != null)
            preOrderTraversal(node.left);
        if (node.right != null)
            preOrderTraversal(node.right);
    }


    private void inOrderTraversal(Node node) {
        if (node == null)
            return;

        if (node.left != null)
            inOrderTraversal(node.left);
        System.out.println(node.data);
        if (node.right != null)
            inOrderTraversal(node.right);
    }


    private void postOrderTraversal(Node node) {
        if (node == null)
            return;

        if (node.left != null)
            postOrderTraversal(node.left);
        if (node.right != null)
            postOrderTraversal(node.right);
        System.out.println(node.data);
    }


    private void levelOrderTraversal(Node node) {
        Deque<Node> q = new ArrayDeque<>();
        if (node != null)
            q.offer(node);

        while (!q.isEmpty()) {
            node = q.poll();
            System.out.println(node.data);

            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
        }

    }

}
