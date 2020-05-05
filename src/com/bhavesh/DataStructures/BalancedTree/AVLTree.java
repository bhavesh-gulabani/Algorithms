/*
 * Implementation of AVL Tree. An AVL tree is a self balancing binary tree
 * which keeps the complexity of all operations logarithmic
 */

package com.bhavesh.DataStructures.BalancedTree;

public class AVLTree<T extends Comparable<T>> {

    private class Node {
        // The balance factor
        private int bf;

        // The value/data contained within the node
        private T value;

        // The height of this node in the tree
        private int height;

        // The left and right children of this node
        private Node left, right;

        public Node(T value) {
            this.value = value;
        }
    }

    // The root of the AVL tree
    private Node root;

    // Tracks the number of nodes in the tree
    private int nodeCount = 0;

    // Returns the height of the tree
    public int height() {
        if (root == null)
            return 0;
        return root.height;
    }

    // Returns the number of nodes in the tree
    public int size() {
        return nodeCount;
    }

    // Returns whether the tree is empty or not
    public boolean isEmpty() {
        return size() == 0;
    }

    // Returns true/false depending on whether a value exists in the tree
    public boolean contains(T value) {
        return contains(root, value);
    }

    // Recursive contains helper method
    private boolean contains(Node node, T value) {

        if (node == null)
            return false;

        // Compare current value to the value in the node
        int compareValue = value.compareTo(node.value);

        // Dig into left subtree
        if (compareValue < 0)
            return contains(node.left, value);

        // Dig into right subtree
        else if (compareValue > 0)
            return contains(node.right, value);

        // Found the value
        return true;
    }


    // Insert a value in the AVL tree, O(log(n))
    public boolean insert(T value) {
        if (value == null)
            return false;
        if (!contains(root, value)) {
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }

    // Private method to insert a value in the AVL tree
    private Node insert(Node node, T value) {

        // Base case
        if (node == null)
            return new Node(value);

        // Compare current value to the value in the node
        int compareValue = value.compareTo(node.value);

        // Insert in the left subtree
        if (compareValue < 0)
            node.left = insert(node.left, value);

        // Insert in the right subtree
        else
            node.right = insert(node.right, value);

        // Update the balance factor and height values
        update(node);

        // Re-balance the tree
        return balance(node);
    }

    // Update a node's height and balance factor
    private void update(Node node) {

        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

        // Update this node's height
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

        // Update balance factor
        node.bf = rightNodeHeight - leftNodeHeight;
    }


    // Re-balance a node if its balance factor is +2 or -2
    private Node balance(Node node) {

        // Left heavy subtree
        if (node.bf == -2) {

            // Left-Left case
            if (node.left.bf <= 0)
                return leftLeftCase(node);

            // Left-Right case
            else
                return leftRightCase(node);
        }

        // Right heavy subtree
        else if (node.bf == +2) {

            // Right-Right case
            if (node.right.bf >= 0)
                return rightRightCase(node);

            // Right-Left case
            else
                return rightLeftCase(node);
        }

        // Node has a balance factor of either 0, +1 or -1
        return node;
    }

    private Node leftLeftCase(Node node) {
        return rightRotation(node);
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return leftLeftCase(node);
    }

    private Node rightRightCase(Node node) {
        return leftRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return rightRightCase(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }

    // Remove a value from this tree if it exists, O(log(n))
    public boolean remove(T element) {

        if (element == null)
            return false;

        if (contains(root, element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }

        return false;
    }

    // Private helper to remove a value from the tree
    private Node remove(Node node, T element) {

        if (node == null)
            return null;

        int compareValue = element.compareTo(node.value);

        // Dig into left subtree
        if (compareValue < 0)
            node.left = remove(node.left, element);

        // Dig into right subtree
        else if (compareValue > 0)
            node.right = remove(node.right, element);

        // Found the node we wish to remove
        else {

            // The case with only a right subtree or no subtree at all
            // In this situation we just swap the node we wish to remove with its right child
            if (node.left == null)
                return node.right;

            // The case with only a left subtree or no subtree at all
            // In this situation we just swap the node we wish to remove with its left child
            else if (node.right == null)
                return node.left;

            // In the case with both subtrees present,
            // the successor node being removed can be either
            // the largest value in the left subtree or
            // the smallest value in the right subtree
            // As a heuristic, we remove from the subtree with greater height
            // which may help with balancing
            else {

                if (node.left.height > node.right.height) {

                    // Swap the value of successor with the node
                    T successorValue = findMax(node.left);
                    node.value = successorValue;

                    // Go into the left subtree and remove the rightmost node we
                    // found and swapped data with. This prevents us from having
                    // two nodes in our tree with the same value.
                    node.left = remove(node.left, successorValue);
                }

                else {

                    // Swap the value of successor with the node
                    T successorValue = findMin(node.right);
                    node.value = successorValue;

                    // Go into the right subtree and remove the leftmost node we
                    // found and swapped data with. This prevents us from having
                    // two nodes in our tree with the same value.
                    node.right = remove(node.right, successorValue);
                }
            }
        }

        // Update balance factor and height values
        update(node);

        // Re-balance the tree
        return balance(node);
    }

    // Helper method to find the leftmost node
    private T findMin(Node node) {
        while (node.left != null)
            node = node.left;

        return node.value;
    }

    // Helper method to find the rightmost node
    private T findMax(Node node) {
        while (node.right != null)
            node = node.right;

        return node.value;
    }

    // To make sure all left child nodes are smaller in value than their parent and
    // all right child nodes are greater in value than their parent.
    // (Used only for testing)
    public boolean validateBSTInvariant(Node node) {
        if (node == null) return true;
        T val = node.value;
        boolean isValid = true;

        if (node.left != null)
            isValid = isValid && node.left.value.compareTo(val) < 0;

        if (node.right != null)
            isValid = isValid && node.right.value.compareTo(val) > 0;

        return isValid && validateBSTInvariant(node.left) && validateBSTInvariant(node.right);
    }
}