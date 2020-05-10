/*
 * An implementation of an iterative approach to DFS with an adjacency list representation of a graph
 * Time complexity: O(V + E)
 */

package com.bhavesh.GraphTheory;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearchIterative {

    // Perform a depth first search on the graph counting
    // the number of nodes traversed starting at some position
    public static int dfs(int start, GraphAdjacencyList graph) {
        int count = 0;
        final int N = graph.getNumNodes();
        boolean[] visited = new boolean[N];
        Stack<Integer> stack = new Stack<>();

        // Start by visiting the start node
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            count++;
            List<GraphAdjacencyList.Edge> edges = graph.getListOfEdges(node);

            if (edges != null) {
                for (GraphAdjacencyList.Edge edge : edges) {
                    if (!visited[edge.to]) {
                        stack.push(edge.to);
                        visited[edge.to] = true;
                    }
                }
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


