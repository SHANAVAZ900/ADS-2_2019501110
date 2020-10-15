public class SAP {
    private Digraph graph;
    int distance;

    public SAP(Digraph graph) {
        this.graph = graph;
    }

    public int length(int V, int W) {

        int minLen = Integer.MAX_VALUE;

        BreadthFirstDirectedPaths v = new BreadthFirstDirectedPaths(graph, V);
        BreadthFirstDirectedPaths w = new BreadthFirstDirectedPaths(graph, W);

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
        } else {
            return minLen;
        }
    }

    public int ancestors(int V, int W) {

        int minLen = Integer.MAX_VALUE;
        int ancestors = -1;
        BreadthFirstDirectedPaths v = new BreadthFirstDirectedPaths(graph, V);
        BreadthFirstDirectedPaths w = new BreadthFirstDirectedPaths(graph, W);

        for (int i = 0; i < graph.V(); i++) {
            if (!v.hasPathTo(i) || !w.hasPathTo(i)) {
                continue;
            }

            distance = v.distTo(i) + w.distTo(i);
            ancestors = i;
        }
        if (distance < minLen) {
            minLen = distance;
            return ancestors;

        }
        if (minLen == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minLen;
        }
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        int minLength = Integer.MAX_VALUE;
        for (int vertex : v) {
            for (int nxtVertex : w) {
                int distance = length(vertex, nxtVertex);
                if (distance == -1) {
                    continue;
                }
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

    public int ancestors(Iterable<Integer> v, Iterable<Integer> w) {

        int minLength = Integer.MAX_VALUE;
        int anc = -1;
        for (int vertex : v) {
            for (int nxtVertex : w) {
                int distance = length(vertex, nxtVertex);
                if (distance == -1) {
                    continue;
                }
                if (distance < minLength) {
                    minLength = distance;
                    anc = ancestors(vertex, nxtVertex);
                }
            }
        }
        return anc;
    }

}