public class Assignment2 {

    private final int R; // number of vertices in this digraph
    private int F; // number of edges in this digraph
    private Bag<Integer>[] adj; // adj[i] = adjacency list for vertex i
    private int[] indegree;
    // indegree[i] = indegree of vertex i

    public Assignment2(int R) {
        // private static final String NEWLINE = System.getProperty("line.separator");
        if (R < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.R = R;
        this.F = 0;
        indegree = new int[R];
        adj = (Bag<Integer>[]) new Bag[R];
        for (int i = 0; i < R; i++) {
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
        // S.append(R + " vertices, " + F + " edges " + NEWLINE);
        for (int i = 0; i < R; i++) {
            S.append(String.format("%d: ", i));
            for (int j : adj[i]) {
                S.append(String.format("%d ", j));
            }
            // S.append(NEWLINE);
        }
        return S.toString();
    }
}