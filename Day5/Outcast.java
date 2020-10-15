import edu.princeton.cs.algs4.In;

public class Outcast {
    private final WordNet net;

    public Outcast(WordNet wordnet) {
        this.net = wordnet;

    }

    public String outcast(String[] nouns) {
        if (nouns == null)
            throw new java.lang.NullPointerException();
        int sum = 0;
        String out = "";

        // Check every noun against every other noun
        for (int i = 0; i < nouns.length; i++) {
            int d = 0;

            // Note that when i = j, distance(i,j) = 0
            for (int j = 0; j < nouns.length; j++) {
                d += net.distance(nouns[i], nouns[j]);
            }

            // Keep track of the current furthest outlier
            if (d > sum) {
                sum = d;
                out = nouns[i];
            }
        }
        return out;
    }

    public static void main(String[] args) {
        WordNet net = new WordNet(args[0], args[1]);
        Outcast out = new Outcast(net);
        for (int i = 2; i < args.length; i++) {
            In in = new In(args[i]);
            String[] nouns = in.readAllStrings();
            System.out.println(args[i] + " :" + out.outcast(nouns));
        }

    }
}
