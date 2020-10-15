import java.util.NoSuchElementException;

public class Digraph {

    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V; // number of vertices in this digraph
    private int E; // number of edges in this digraph
    Bag<Integer>[] adj; // adj[v] = adjacency list for vertex v

    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */

    public Digraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a digraph from the specified input stream. The format is the
     * number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by
     * whitespace.
     *
     * @param in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in
     *                                  prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is
     *                                  negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public Digraph(In in) {
        if (in == null)
            throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0)
                throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");

            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() {
        return E;
    }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param G the digraph to copy
     * @throws IllegalArgumentException if {@code G} is {@code null}
     */
    public Digraph(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException("argument is null");

        this.V = G.V();
        this.E = G.E();
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");

        // update adjacency lists
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * 
     * @param v vertex
     * @param w edges, This method adds the vertex and edges connected to it.
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges
     *         <em>E</em>, followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("Vertex: " + "%d " + "is connected to : ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}