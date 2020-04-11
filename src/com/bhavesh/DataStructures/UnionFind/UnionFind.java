/*
 * UnionFind/Disjoint Set data structure implementation
 */

package com.bhavesh.DataStructures.UnionFind;

public class UnionFind {

    // The number of elements in the union find
    private int size;

    // Array to keep track of the size of each component
    private int[] componentSize;

    // Array to keep track of root of elements
    // id[i] points to parent of i, if if id[i] = i then i is a root node
    private int[] id;

    // The number of components in the union find
    private int numberOfComponents;

    public UnionFind(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size should be positive");

        this.size = numberOfComponents = size;
        componentSize = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;              // Link to itself
            componentSize[i] = i;   // Each component is originally of size one
        }
    }

    // Find which component/set 'p' belongs to, takes amortized constant time
    public int find(int p) {

        // Find the root of the component
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        // Compress the path leading back to the root, also known as "path compression".
        // This gives us amortized time complexity
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    // This is an alternative recursive formulation for the find method
//    public int find(int p) {
//       if (p == id[p]) return p;
//       return id[p] = find(id[p]);
//    }

    // Return whether the elements 'p' and 'q' are in the same component
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Return the size of the component 'p' belongs to
    public int componentSize(int p) {
        return componentSize[find(p)];
    }

    // Return the number of elements in this UnionFind
    public int size() {
        return size;
    }

    // Return the number of remaining components
    public int components() {
        return numberOfComponents;
    }

    // Unify the components containing elements 'p' and 'q'
    public void unify(int p, int q) {

        // When the elements are already in the same group
        if (connected(p, q))
            return;

        int root1 = find(p);
        int root2 = find(q);

        // Merge smaller component into the larger one
        if (componentSize[root1] < componentSize[root2]) {
            componentSize[root2] += componentSize[root1];
            id[root1] = root2;
        }
        else {
            componentSize[root1] += componentSize[root2];
            id[root2] = root1;
        }

        // Since the roots found are different we know that the
        // number of components has decreased by one
        numberOfComponents--;
    }
}

