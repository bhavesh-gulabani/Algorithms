/*
 * An implementation of a recursive approach to DFS with an adjacency list representation of a graph
 * Time complexity: O(V + E)
 */

package com.bhavesh.GraphTheory;

import java.util.List;

public class DepthFirstSearchRecursive {

    // Perform a depth first search on the graph counting
    // the number of nodes traversed starting at some position
    public static long dfs(int start, GraphAdjacencyList graph) {
        final int N = graph.getNumNodes();
        boolean[] visited = new boolean[N];
        return dfs(start, visited, graph);
    }

    // Actual recursive function to perform the traversal
    private static long dfs(int at, boolean[] visited, GraphAdjacencyList graph) {

        if (visited[at]) return 0L;

        visited[at] = true;
        // System.out.println(at);
        long count = 1;


        // Visit all the edges adjacent to where we are at
        List<GraphAdjacencyList.Edge> edges = graph.getListOfEdges(at);
        if (edges != null) {
            for (GraphAdjacencyList.Edge edge : edges) {
                count += dfs(edge.to, visited, graph);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList(5);

        graph.addDirectedEdge(0, 1, 3);
        graph.addDirectedEdge(1, 2, 5);
        graph.addDirectedEdge(1, 3, 7);
        graph.addDirectedEdge(2, 3, 2);
        graph.addDirectedEdge(2, 4, 7);
        graph.addDirectedEdge(2, 2, 6);

        long nodeCount = dfs(0, graph);
        System.out.println("DFS node count starting at node 0: " + nodeCount);
        if (nodeCount != 5) System.err.println("Error with DFS");

        nodeCount = dfs(4, graph);
        System.out.println("DFS node count starting at node 4: " + nodeCount);
        if (nodeCount != 1) System.err.println("Error with DFS");
    }
}
