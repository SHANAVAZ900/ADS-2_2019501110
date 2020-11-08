import java.util.LinkedList;

public class Radix {

    public static LinkedList<Character> getAsciiLinkedList() {
        LinkedList<Character> ll = new LinkedList<>();
        for (char c = 0; c < 256; c++) {
            ll.add(c);
        }
        return ll;
    }
}