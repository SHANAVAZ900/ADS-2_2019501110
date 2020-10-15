import java.io.*;
import java.util.*;

public class Hypernym {
    static ArrayList<String> key = new ArrayList<String>();
    static ArrayList<String[]> val = new ArrayList<String[]>();

    public static void main(String[] args) throws Exception {

        Hypernym word = new Hypernym();
        String file = "D:\\ADS-2\\ADS-2_2019501110\\Day2\\Assignment2\\wordNet\\hypernyms.txt";
        word.Hypernyms(file);
        Digraph a2 = new Digraph(key.size());
        int c = 0;
        for (int i = 0; i < key.size(); i++) {
            if (val.get(i) != null) {
                for (int j = 0; j < val.get(i).length; j++) {
                    int x = Integer.parseInt(key.get(i));
                    int y = Integer.parseInt(val.get(i)[j]);
                    a2.addEdge(x, y);
                    c++;
                }
            }
        }
        System.out.print("Edges : " + c);
    }

    public void Hypernyms(String file) throws Exception {

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String data;
        int c = 0;
        while ((data = br.readLine()) != null) {
            String[] str = data.split(",", 2);
            c++;
            if (str.length > 1) {
                key.add(str[0]);
                val.add(str[1].split(","));
            } else {
                key.add(str[0]);
                val.add(null);
            }
        }
        System.out.println("Vertices : " + c);
    }
}