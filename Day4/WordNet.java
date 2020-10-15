import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private final ArrayList<String> sysnlist;
    private final Map<String, ArrayList<Integer>> sysnMap;
    private final Digraph graph;
    private SAP sap;

    public WordNet(String synsets, String hypernyms) {
        sysnlist = new ArrayList<String>();
        sysnMap = new HashMap<String, ArrayList<Integer>>();

        In sysnIn = new In(synsets);
        while (sysnIn.hasNextLine()) {
            String line = sysnIn.readLine();
            String[] temp = line.split(",");
            int ids = Integer.parseInt(temp[0]);
            if (sysnlist.size() < ids) {
                sysnlist.ensureCapacity(ids + 1);
            }
            sysnlist.add(temp[1]);
            String[] nouns = temp[1].split(" ");
            for (String noun : nouns) {
                if (!sysnMap.containsKey(noun)) {
                    sysnMap.put(noun, new ArrayList<Integer>());
                }
                sysnMap.get(noun).add(ids);
            }

        }

        sysnIn.close();

        graph = new Digraph(sysnlist.size());
        int[] res = new int[sysnlist.size()];

        In hyperIn = new In(hypernyms);
        while (hyperIn.hasNextLine()) {
            String line = hyperIn.readLine();
            String[] temp = line.split(",");
            int vertex = Integer.parseInt(temp[0]);
            res[vertex] += temp.length - 1;

            for (int i = 1; i < temp.length; ++i) {
                int words = Integer.parseInt(temp[i]);
                graph.addEdge(vertex, words);
            }
        }
        hyperIn.close();

        if (!rootedDAG(res)) {
            throw new IllegalArgumentException();
        }

        sap = new SAP(graph);
    }

    private boolean rootedDAG(int[] outputs) {
        int rootCount = 0;
        for (int v = 0; v < graph.V(); ++v) {
            if (outputs[v] == 0) {
                ++rootCount;
            }
        }
        if (rootCount != 1) {
            return false;
        }

        return true;

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return sysnMap.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return sysnMap.containsKey(word);
    }

    // returns distance between nounA and nounB
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (sysnMap.containsKey(nounA) && sysnMap.containsKey(nounB)) {
            return sap.length(sysnMap.get(nounA), sysnMap.get(nounB));
        } else {
            throw new IllegalArgumentException("Word 1 : " + nounA + "and Word2 : " + nounB);
        }

    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        return sysnlist.get(sap.ancestor(sysnMap.get(nounA), sysnMap.get(nounB)));
    }

}