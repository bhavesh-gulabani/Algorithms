/*
 * Implementation of an adjacency list representation of a directed weighted graph
 */

package com.bhavesh.GraphTheory.DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAdjacencyList {
    Map<Integer, List<Edge>> graph = new HashMap<>();
    int numNodes;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public GraphAdjacencyList(int numNodes) {
        this.numNodes = numNodes;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public List<Edge> getListOfEdges(int at) {
        return graph.get(at);
    }

    public void addDirectedEdge(int from, int to, int cost) {
        List<Edge> list = graph.get(from);
        if (list == null) {
            list = new ArrayList<Edge>();
            graph.put(from, list);
        }
        list.add(new Edge(from, to, cost));
    }
}

