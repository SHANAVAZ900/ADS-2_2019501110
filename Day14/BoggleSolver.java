import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.SET;

public class BoggleSolver {

    private TrieSET dictionary;

    // Initializes the data structure using the given array of strings as the
    // dictionary.
    // (You can assume each word in the dictionary contains only the uppercase
    // letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        this.dictionary = new TrieSET();

        for (String word : dictionary) {
            this.dictionary.add(word);
        }

    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> valid_words = new HashSet<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] visited = new boolean[board.rows()][board.cols()];
                solver(board, i, j, visited, "", valid_words);
            }
        }
        return valid_words;

    }

    private void solver(BoggleBoard board, int row, int col, boolean[][] visited, String pre, Set<String> set) {

        if (visited[row][col]) {
            return;
        }

        char letter = board.getLetter(row, col);
        String w = pre;

        if (letter == 'Q') {
            w = w + "QU";
        } else {
            w = w + letter;
        }
        if (!dictionary.hasPrefix(w)) {
            return;
        }
        if (w.length() > 2 && dictionary.contains(w)) {
            set.add(w);
        }
        visited[row][col] = true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if ((row + i >= 0) && (row + i < board.rows() && (col + j >= 0) && (col + j < board.cols()))) {
                    solver(board, row + i, col + j, visited, w, set);
                }
            }
        }
        visited[row][col] = false;
    }

    // Returns the score of the given word if it is in the dictionary, zero
    // otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (dictionary.contains(word)) {
            switch (word.length()) {
                case 0:
                case 1:
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                case 6:
                    return 3;
                case 7:
                    return 5;
                default:
                    return 11;
            }
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {

        // BoggleSolver b = new BoggleSolver();

    }
}
