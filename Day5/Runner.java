class Runner {

    public static void main(String[] args) {

        In input = new In("D:\\ADS-2\\ADS-2_2019501110\\Day3\\wordNet\\digraph1.txt");
        Digraph graph = new Digraph(input);
        SAP s = new SAP(graph);
        System.out.println("length: " + s.length(3, 11) + " Ancestor: " + s.ancestors(3, 11));
        System.out.println("length: " + s.length(1, 6) + " Ancestor: " + s.ancestors(1, 6));

    }
}