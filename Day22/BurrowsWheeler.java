import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    public static void transform() {
        // Read the string to Burrows-Wheeler transform from standard input
        String inString = BinaryStdIn.readString();

        // Construct a CircularSuffixArray from the input string
        CircularSuffixArray csa = new CircularSuffixArray(inString);

        int index, first, prev;
        first = -1;
        StringBuilder sb = new StringBuilder();

        // Iterate over the rows in the sorted Circular Suffix Array
        for (int i = 0; i < inString.length(); i++) {

            index = csa.index(i);

            if (index == 0)
                first = i;

            prev = index - 1;
            if (prev < 0)
                prev += inString.length();

            // Append the last character in this suffix array to our response.
            sb.append(inString.charAt(prev));
        }

        // Catch situations where we haven't actually found first yet
        if (first < 0)
            throw new java.lang.IllegalArgumentException("'first' is negative!");

        BinaryStdOut.write(first);

        BinaryStdOut.write(sb.toString());

        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input

    public static void inverseTransform() {

        int first = BinaryStdIn.readInt();

        // Instantiate an array to use in key-indexed counting.
        int R = 256;
        int[] count = new int[R + 1];

        String inString = BinaryStdIn.readString();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            count[c + 1]++;
        }

        // for (int r = 0; r < R; r++) {
        // count[r + 1] += count[r];
        // }

        char[] aux = new char[inString.length()];
        int[] next = new int[inString.length()];
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            aux[count[c]] = c;
            next[count[c]] = i;
            count[c]++;
        }

        int ptr = first;
        for (int i = 0; i < inString.length(); i++) {
            BinaryStdOut.write(aux[ptr]);
            ptr = next[ptr];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        // if ("-".equals(args[0])) {
        // BurrowsWheeler.transform();
        // } else if ("+".equals(args[0])) {
        // BurrowsWheeler.inverseTransform();
        // }
    }

}
