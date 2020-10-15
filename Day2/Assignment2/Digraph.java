import java.io.*;
import java.util.*;

public class Digraph {

    private final int V; // number of vertices in this digraph
    private int F; // number of edges in this digraph
    private Bag<Integer>[] adj; // adj[i] = adjacency list for vertex i
    private int[] indegree;
    // indegree[i] = indegree of vertex i

    public Digraph(int V) {
        // private static final String NEWLINE = System.getProperty("line.separator");
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.F = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public void addEdge(int i, int j) {
        adj[i].add(j);
        indegree[j]++;
        F++;
    }

    public String toString() {
        StringBuilder S = new StringBuilder();
        // S.append(V + " vertices, " + F + " edges " + NEWLINE);
        for (int i = 0; i < V; i++) {
            S.append(String.format("%d: ", i));
            for (int j : adj[i]) {
                S.append(String.format("%d ", j));
            }
            // S.append(NEWLINE);
        }
        return S.toString();
    }
}