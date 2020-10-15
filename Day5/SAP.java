import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class SAP {
    private final Digraph graph;
    private int distance;

    public SAP(Digraph graph) {
        this.graph = graph;
    }

    private boolean checkBounds(int v) {
        return 0 <= v && v < graph.V();
    }

    public int length(int V, int W) {
        if (!checkBounds(V) || !checkBounds(W)) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        BreadthFirstDirectedPaths v = new BreadthFirstDirectedPaths(graph, V);
        BreadthFirstDirectedPaths w = new BreadthFirstDirectedPaths(graph, W);
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < graph.V(); i++) {
            if (!v.hasPathTo(i) || !w.hasPathTo(i)) {
                continue;
            }
            distance = v.distTo(i) + w.distTo(i);

            if (distance < minLen) {
                minLen = distance;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return -1;
        }
        return minLen;

    }

    public int ancestor(int V, int W) {
        if (!((V >= 0 && V <= graph.V() - 1) && (W >= 0 && W <= graph.V() - 1))) {
            throw new IllegalArgumentException();
        }

        int minLen = Integer.MAX_VALUE;
        int ancestors = -1;
        BreadthFirstDirectedPaths v = new BreadthFirstDirectedPaths(graph, V);
        BreadthFirstDirectedPaths w = new BreadthFirstDirectedPaths(graph, W);

        for (int i = 0; i < graph.V(); i++) {
            if (
                .hasPathTo(i) && w.hasPathTo(i)) {
                int distance = v.distTo(i) + w.distTo(i);
                if (distance < minLen) {
                    minLen = distance;
                    ancestors = i;
                }
            }

        }

        if (minLen == Integer.MAX_VALUE) {
            return -1;

        }
        return ancestors;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        int minLength = Integer.MAX_VALUE;
        for (int vertex : v) {
            for (int nxtVertex : w) {
                int distance = length(vertex, nxtVertex);
                if (distance < minLength) {
                    minLength = distance;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minLength;
        }
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }
        int minLen = Integer.MAX_VALUE;
        int ancestors = -1;
        BreadthFirstDirectedPaths vb = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths wb = new BreadthFirstDirectedPaths(graph, w);

        for (int i = 0; i < graph.V(); i++) {
            if (vb.hasPathTo(i) && wb.hasPathTo(i)) {
                int distance = vb.distTo(i) + wb.distTo(i);
                if (distance < minLen) {
                    minLen = distance;
                    ancestors = i;
                }
            }

        }

        if (minLen == Integer.MAX_VALUE) {
            return -1;
        }
        return ancestors;
    }

    public static void main(String[] args) {

        In input = new In("D:\\ADS-2\\ADS-2_2019501110\\Day5\\wordNet\\digraph1.txt");
        Digraph graph = new Digraph(input);
        SAP s = new SAP(graph);
        System.out.println("length: " + s.length(3, 11) + " Ancestor: " + s.ancestor(3, 11));
        System.out.println("length: " + s.length(1, 6) + " Ancestor: " + s.ancestor(1, 6));

    }

}
