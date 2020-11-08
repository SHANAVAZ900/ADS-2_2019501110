
/******************************************************************************
 *  Compilation:  javac TrieSET.java
 *  Execution:    java TrieSET < words.txt
 *  Dependencies: StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/52trie/shellsST.txt
 *
 *  An set for extended ASCII strings, implemented  using a 256-way trie.
 *
 *  Sample client reads in a list of words from standard input and
 *  prints out each word, removing any duplicates.
 *
 ******************************************************************************/

import java.util.Iterator;

public class TrieSET {
    private static final int R = 256; // extended ASCII

    private Node root; // root of trie
    private int n; // number of keys in trie

    // R-way trie node
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    /**
     * Initializes an empty set of strings.
     */
    public TrieSET() {
    }

    /**
     * Does the set contain the given key?
     * 
     * @param key the key
     * @return {@code true} if the set contains {@code key} and {@code false}
     *         otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(String key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        Node x = get(root, key, 0);
        if (x == null)
            return false;
        return x.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * Adds the key to the set if it is not already present.
     * 
     * @param key the key to add
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(String key) {
        if (key == null)
            throw new IllegalArgumentException("argument to add() is null");
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null)
            x = new Node();
        if (d == key.length()) {
            if (!x.isString)
                n++;
            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }

    /**
     * Returns the number of strings in the set.
     * 
     * @return the number of strings in the set
     */
    public int size() {
        return n;
    }

    /**
     * Is the set empty?
     * 
     * @return {@code true} if the set is empty, and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean hasPrefix(String s) {
        Node x = get(root, s, 0);
        return x != null;

    }

}
