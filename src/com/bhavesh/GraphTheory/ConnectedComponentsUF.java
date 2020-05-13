/*
 * Implementation of an algorithm to find all connected components of an undirected graph.
 * The approach uses a union find data structure to merge together nodes connected by an edge.
 */

package com.bhavesh.GraphTheory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectedComponentsUF {
    static int countConnectedComponents(GraphAdjacencyList graph) {
        final int n = graph.getNumNodes();
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            List<GraphAdjacencyList.Edge> edges = graph.getListOfEdges(i);
            if (edges != null) {
                for (GraphAdjacencyList.Edge edge : edges) {
                    uf.unify(edge.from, edge.to);
                }
            }
        }
        return uf.components();
    }

    public static void main(String[] args) {

        final int n = 11;
        GraphAdjacencyList graph = new GraphAdjacencyList(n);

        // Setup a graph with four connected components:
        // {0,1,5,6}, {2,3,4}, {7,9}, {8,10}
        graph.addUndirectedEdge(0, 1, 2);
        graph.addUndirectedEdge(1, 5, 4);
        graph.addUndirectedEdge(5, 6, 5);
        graph.addUndirectedEdge(6, 0, 2);
        graph.addUndirectedEdge(2, 3, 1);
        graph.addUndirectedEdge(3, 4, 2);
        graph.addUndirectedEdge(7, 9, 6);
        graph.addUndirectedEdge(8, 10, 6);

        int components = countConnectedComponents(graph);
        System.out.println("Number of components : " + components);
    }

}

class GraphAdjacencyList {
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

    public void addUndirectedEdge(int from, int to, int cost) {
        List<Edge> list = graph.get(from);
        if (list == null) {
            list = new ArrayList<Edge>();
            graph.put(from, list);
        }
        list.add(new Edge(from, to, cost));
        list.add(new Edge(to, from, cost));
    }
}

// Union find data structure
class UnionFind {

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

