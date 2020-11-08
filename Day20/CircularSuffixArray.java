import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    private final int length;
    private final Integer[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new java.lang.IllegalArgumentException("Constructor cannot be null");
        }

        length = s.length();
        index = new Integer[length];

        for (int i = 0; i < length; i++) {
            index[i] = i;
        }

        Arrays.sort(index, (Integer t, Integer t1) -> {
            for (int i = 0; i < length; i++) {

                char c = s.charAt((t + i) % length);
                char c1 = s.charAt((t1 + i) % length);

                if (c < c1)
                    return -1;
                if (c > c1)
                    return 1;

            }

            return t.compareTo(t1);
        });
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length) {
            throw new java.lang.IllegalArgumentException("i must be in the range of [0,s.length())");
        }
        return index[i];
    }

    // public static void main(String[] args) {
    // String s = "BBBBABBBBB";
    // CircularSuffixArray csa = new CircularSuffixArray(s);
    // StdOut.println(csa.length());

    // int[] a = new int[s.length()];
    // for (int i = 0; i < s.length(); i++) {
    // a[i] = csa.index(i);
    // StdOut.println("Index of " + i + " is " + a[i]);
    // }
    // }
}