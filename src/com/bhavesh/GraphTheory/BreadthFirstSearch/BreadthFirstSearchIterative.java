/*
 * An implementation of an iterative approach to BFS with an adjacency list representation of a graph
 * Time complexity: O(V + E)
*/

package com.bhavesh.GraphTheory.BreadthFirstSearch;

import java.util.*;

public class BreadthFirstSearchIterative {

    // Reconstructs the path (of nodes) from 'start' to 'end' inclusive.
    // If the edges are unweighted then this method returns the shortest path
    // from 'start' to 'end'
    public static List<Integer> reconstructPath(GraphAdjacencyList graph, int start, int end) {
        Integer[] prev = bfs(graph, start);
        List<Integer> path = new ArrayList<>();
        for (Integer at = end; at != null; at = prev[at])
            path.add(at);

        Collections.reverse(path);

        if (path.get(0) == start) return path;
        path.clear();
        return path;
    }


    private static Integer[] bfs(GraphAdjacencyList graph, int start) {
        final int N = graph.getNumNodes();
        Integer[] prev = new Integer[N];
        boolean[] visited = new boolean[N];
        Deque<Integer> queue = new ArrayDeque<>(N);

        // Start by visiting the 'start' node and add it to the queue
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> neighbours = graph.getListOfNodes(node);

            for (Integer next : neighbours) {
                if (!visited[next]) {
                    visited[next] = true;
                    prev[next] = node;
                    queue.offer(next);
                }
            }
        }
        return prev;
    }


    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList(5);

        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(2, 2);

        int start = 4;
        int end = 1;
        System.out.println("Shortest path from " + start + " to " + end + " is: " + reconstructPath(graph, start, end));
    }
}