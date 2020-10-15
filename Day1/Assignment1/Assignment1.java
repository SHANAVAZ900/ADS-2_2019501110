import java.io.*;

class Assignment1 {
    String[] str;

    public static void main(String args[]) throws Exception {
        // defines files and calls the methods
        Assignment1 AS1 = new Assignment1();
        String synsets = "D:\\ADS-2\\ADS-2_2019501110\\Day1\\Assignment1\\wordNet\\synsets.txt";
        String hypernyms = "D:\\ADS-2\\ADS-2_2019501110\\Day1\\Assignment1\\wordNet\\hypernyms.txt";
        AS1.synsets_file(synsets);
        AS1.hypernyms_file(hypernyms);
    }

    private void synsets_file(String file) throws Exception {
        // uses the keys value method after splitting each line.
        FileReader file_reader = new FileReader(file);
        BufferedReader buffer_reader = new BufferedReader(file_reader);
        String j;
        // String[] str = new String[100];
        String[] keys = new String[100];
        String[] values = new String[100];
        int p = 0;
        int q = 0;
        while ((j = buffer_reader.readLine()) != null) {
            int i = 0;
            if (p < 5) {
                System.out.println(j);
                str = j.split(",", 2);
                keys[q] = str[i];
                values[q] = str[i + 1];
            }
            i++;
            q++;
            p = p + 1;
        }
        for (int d = 0; d < 5; d++)
            System.out.println(keys[d] + " : {" + values[d] + "}");
        file_reader.close();
    }

    private void hypernyms_file(String file) throws Exception {
        //
        FileReader file_reader = new FileReader(file);
        BufferedReader buffer_reader = new BufferedReader(file_reader);
        String j;
        int p = 0;
        while ((j = buffer_reader.readLine()) != null) {
            int i = 0;
            if (p < 5) {
                // System.out.println(j);
                str = j.split(",", 2);
                for (int d = 0; d < str.length; d += 2)
                    for (int e = 1; e < str.length; e += 2)
                        System.out.println(str[d] + " : " + str[e]);
            }
            p = p + 1;
        }

        file_reader.close();
    }
}