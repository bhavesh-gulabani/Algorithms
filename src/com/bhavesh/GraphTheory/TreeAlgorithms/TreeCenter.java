/*
 * An algorithm to find the center(s) of a tree
 *
 * Time complexity: O(V + E)
 */

package com.bhavesh.GraphTheory.TreeAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeCenter {

    public static List<Integer> findTreeCenters(List<List<Integer>> tree) {
        final int n = tree.size();
        int[] degree = new int[n];

        // Find all leaf nodes
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> edges = tree.get(i);
            degree[i] = edges.size();
            if (degree[i] <= 1) {
                leaves.add(i);
                degree[i] = 0;
            }
        }

        int processedLeaves = leaves.size();

        // Remove leaf nodes and decrease the degree of each node adding new leaf
        // nodes progressively until only the center(s) remain
        while (processedLeaves < n) {
            List<Integer> newLeaves = new ArrayList<>();
            for (int node : leaves) {
                for (int neighbour : tree.get(node)) {
                    if (--degree[neighbour] == 1) {
                        newLeaves.add(neighbour);
                    }
                }
                degree[node] = 0;
            }
            processedLeaves += newLeaves.size();
            leaves = newLeaves;
        }

        return leaves;
    }

    /* ******** TESTING ******** */

    // Create an empty tree as an adjacency list
    public static List<List<Integer>> createEmptyTree(int n) {
        List<List<Integer>> tree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) tree.add(new LinkedList<>());
        return tree;
    }

    public static void addUndirectedEdge(List<List<Integer>> tree, int from, int to) {
        tree.get(from).add(to);
        tree.get(to).add(from);
    }

    public static void main(String[] args) {

        List<List<Integer>> graph = createEmptyTree(9);
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 2, 1);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 5, 3);
        addUndirectedEdge(graph, 2, 6);
        addUndirectedEdge(graph, 6, 7);
        addUndirectedEdge(graph, 6, 8);

        // Center is 2
        System.out.println(findTreeCenters(graph));

        // Center is 0
        List<List<Integer>> graph2 = createEmptyTree(1);
        System.out.println(findTreeCenters(graph2));

        // Centers are 2,3
        List<List<Integer>> graph3 = createEmptyTree(7);
        addUndirectedEdge(graph3, 0, 1);
        addUndirectedEdge(graph3, 1, 2);
        addUndirectedEdge(graph3, 2, 3);
        addUndirectedEdge(graph3, 3, 4);
        addUndirectedEdge(graph3, 4, 5);
        addUndirectedEdge(graph3, 4, 6);
        System.out.println(findTreeCenters(graph3));
    }

}
