/*
 * An implementation of a recursive approach to BFS with an adjacency list representation of a graph
 * Time complexity: O(V + E)
 */

package com.bhavesh.GraphTheory.BreadthFirstSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BreadthFirstSearchRecursive {

    // Each breadth first search layer gets separated by a DEPTH_TOKEN
    // DEPTH_TOKENs help count the distance from one node to another because
    // we can increment the depth counter each time a DEPTH_TOKEN is encountered
    public static int DEPTH_TOKEN = -1;

    // Computes the eccentricity (distance to furthest node) from the starting node
    public static int bfs(GraphAdjacencyList graph, int start) {
        final int N = graph.getNumNodes();
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        queue.offer(DEPTH_TOKEN);
        return bfs(graph, visited, queue);
    }

    private static int bfs(GraphAdjacencyList graph, boolean[] visited, Queue<Integer> queue) {

        int at = queue.poll();

        if (at == DEPTH_TOKEN) {
            queue.offer(DEPTH_TOKEN);
            return 1;
        }

        // Node already visited
        if (visited[at]) return 0;

        // Visit this node
        visited[at] = true;

        // Add all neighbours to the queue
        List<Integer> neighbours = graph.getListOfNodes(at);
        if (neighbours != null) {
            for (int next : neighbours) {
                if (!visited[next])
                    queue.add(next);
            }
        }

        int depth = 0;

        while (true) {

            // Stop when the queue is empty
            if (queue.size() == 1 && queue.peek() == DEPTH_TOKEN) break;

            // The depth is the sum of all DEPTH_TOKENS encountered
            depth += bfs(graph, visited, queue);
        }

        return depth;
    }


    public static void main(String[] args) {
        GraphAdjacencyList graph = new GraphAdjacencyList(5);

        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(2, 2);

        System.out.println("BFS depth: " + bfs(graph, 0));
    }
}
