import java.util.LinkedList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing
    // to standard output
    public static void encode() {
        // Initialize a LinkedList in lexicographic order to store
        LinkedList<Character> ll = Radix.getAsciiLinkedList();

        int move_to_front_index;
        while (!BinaryStdIn.isEmpty()) {

            char ascii_character = BinaryStdIn.readChar();

            move_to_front_index = ll.indexOf(ascii_character);
            ll.remove(move_to_front_index);
            ll.addFirst(ascii_character);

            BinaryStdOut.write(move_to_front_index, 8);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing
    // to standard output
    public static void decode() {
        // Initialize a LinkedList in lexicographic order to store
        // the moved-to-front state
        LinkedList<Character> ll = Radix.getAsciiLinkedList();

        char ascii_character;
        while (!BinaryStdIn.isEmpty()) {

            char move_to_front_index = BinaryStdIn.readChar();

            ascii_character = ll.remove((int) move_to_front_index);
            ll.addFirst(ascii_character);

            BinaryStdOut.write(ascii_character);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if ("-".equals(args[0])) {
            MoveToFront.encode();
        } else if ("+".equals(args[0])) {
            MoveToFront.decode();
        }
    }

}