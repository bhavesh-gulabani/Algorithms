/*
 * This implementation of topological sort takes an adjacency list of an acyclic graph and returns an
 * array with the indices of the nodes in a (non unique) topological order which tells us how to
 * process nodes in the graph based on dependencies indicated by directed edges.
 *
 * Time complexity: O(V + E)
 */

package com.bhavesh.GraphTheory;

import java.util.*;

public class TopologicalSort {

    // Helper method that performs a depth first search on the graph
    // to give us the topological ordering we want
    private static int dfs(
            int i, int at, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph ) {

        visited[at] = true;
        List<Edge> edges = graph.get(at);

        if (edges != null) {
            for (Edge edge : edges) {
                if (!visited[edge.to])
                    i = dfs(i, edge.to, visited, ordering, graph);
            }
        }
        ordering[i] = at;
        return i - 1;
    }

    // Finds a topological ordering of the nodes in a Directed Acyclic Graph (DAG)
    // The input is an adjacency list representing the graph and the number of nodes
    // in the graph
    public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int n) {

        int[] ordering = new int[n];
        boolean[] visited = new boolean[n];

        int i = n - 1;
        for (int at = 0; at < n; at++) {
            if (!visited[at])
                i = dfs(i, at, visited, ordering, graph);
        }
        return ordering;
    }

    // A useful application of the topological sort is to find the shortest
    // path between two nodes in a DAG. Given an adjacency list this method
    // finds the shortest path to all nodes starting at 'start'
    public static Integer[] dagShortestPath(Map<Integer, List<Edge>> graph, int start, int n) {

        int[] topSort = topologicalSort(graph, n);
        Integer[] dist = new Integer[n];
        dist[start] = 0;

        for (int i = 0; i < n; i++) {

            int nodeIndex = topSort[i];
            if (dist[nodeIndex] != null) {
                List<Edge> adjacentEdges = graph.get(nodeIndex);
                if (adjacentEdges != null) {
                    for (Edge edge : adjacentEdges) {

                        int newDist = dist[nodeIndex] + edge.weight;
                        if (dist[edge.to] == null)
                            dist[edge.to] = newDist;
                        else
                            dist[edge.to] = Math.min(dist[edge.to], newDist);
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        // Graph setup
        final int N = 7;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < N; i++)
            graph.put(i, new ArrayList<>());

        graph.get(0).add(new Edge(0, 1, 3));
        graph.get(0).add(new Edge(0, 2, 2));
        graph.get(0).add(new Edge(0, 5, 3));
        graph.get(1).add(new Edge(1, 3, 1));
        graph.get(1).add(new Edge(1, 2, 6));
        graph.get(2).add(new Edge(2, 3, 1));
        graph.get(2).add(new Edge(2, 4, 10));
        graph.get(3).add(new Edge(3, 4, 5));
        graph.get(5).add(new Edge(5, 4, 7));

        int[] ordering = topologicalSort(graph, N);

        System.out.println(Arrays.toString(ordering));

        // Find the shortest path starting at node 0
        Integer[] shortestPath = dagShortestPath(graph, 0, N);

        // Every index i of shortestPath array gives the shortest distance from start node 0
        // to the node with label i
        System.out.println(Arrays.toString(shortestPath));

    }

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
