/*
 * Implementation of an adjacency list representation of an undirected graph
 */

package com.bhavesh.GraphTheory.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjacencyList {
    int numNodes;
    List<List<Integer>> graph;

    public GraphAdjacencyList(int numNodes) {
        this.numNodes = numNodes;

        graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++)
            graph.add(new ArrayList<>());
    }

    public int getNumNodes() {
        return numNodes;
    }

    public List<Integer> getListOfNodes(int at) {
        return graph.get(at);
    }

    public void addUndirectedEdge(int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }
}
