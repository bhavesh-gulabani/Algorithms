/*
 * Implementation of an algorithm to find all connected components of an undirected graph.
 */

package com.bhavesh.GraphTheory;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentsDFS {

    private final int n;

    private int componentCount;
    private int[] components;
    private boolean solved;
    private boolean[] visited;
    private List<List<Integer>> graph;

    public ConnectedComponentsDFS(List<List<Integer>> graph) {
        if (graph == null) throw new NullPointerException();
        this.n = graph.size();
        this.graph = graph;
        this.solved = false;
    }

    public int[] getComponents() {
        solve();
        return components;
    }

    public int countComponents() {
        solve();
        return componentCount;
    }

    public void solve() {
        if (solved) return;

        visited = new boolean[n];
        components = new int[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                componentCount++;
                dfs(i);
            }
        }
        solved = true;
    }

    private void dfs(int at) {
        visited[at] = true;
        components[at] = componentCount;

        for (int to : graph.get(at)) {
            if (!visited[to])
                dfs(to);
        }
    }

    public static List<List<Integer>> createGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        return graph;
    }

    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }


    public static void main(String[] args) {
        final int n = 11;
        List<List<Integer>> graph = createGraph(n);

        // Setup a graph with four connected components:
        // {0,1,5,6}, {2,3,4}, {7,9}, {8,10}
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 1, 5);
        addUndirectedEdge(graph, 5, 6);
        addUndirectedEdge(graph, 6, 0);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 7, 9);
        addUndirectedEdge(graph, 8, 10);

        ConnectedComponentsDFS solver;
        solver = new ConnectedComponentsDFS(graph);

        int count = solver.countComponents();
        System.out.println("Number of components : " + count);

        int[] components = solver.getComponents();
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + " is a part of component " + components[i]);
        }
    }
}