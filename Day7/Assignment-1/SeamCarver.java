import java.util.Arrays;
import java.awt.Color;
// import edu.princeton.cs.algs4.Picture;
import java.lang.Math;

public class SeamCarver {

    private Picture pht;

    private double[][] pixelEnergy;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        pht = new Picture(picture);

        pixelEnergy = new double[height()][width()];

        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {

                pixelEnergy[row][col] = energy(col, row);

            }

        }
        System.out.println(Arrays.deepToString(pixelEnergy));

    }

    // current picture
    public Picture picture() {
        return pht;
    }

    // width of current picture
    public int width() {
        return pht.width();

    }

    // height of current picture
    public int height() {
        return pht.height();

    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) { // x = 1, y =2 w=3 h =10
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;

        }

        Color left = pht.get(x - 1, y);
        Color right = pht.get(x + 1, y);
        Color top = pht.get(x, y - 1);
        Color bottom = pht.get(x, y + 1);
        // horizontal
        int Rxa = right.getRed();
        int Gxa = right.getGreen();
        int Bxa = right.getBlue();

        int Rxb = left.getRed();
        int Gxb = left.getGreen();
        int Bxb = left.getBlue();

        int gradiant_Rx = Rxa - Rxb;
        int gradiant_Gx = Gxa - Gxb;
        int gradiant_Bx = Bxa - Bxb;

        int gradiant_result = (gradiant_Rx * gradiant_Rx) + (gradiant_Gx * gradiant_Gx) + (gradiant_Bx * gradiant_Bx);

        // vertical
        int Rx_down = bottom.getRed();
        int Gx_down = bottom.getGreen();
        int Bx_down = bottom.getBlue();

        int Rx_above = top.getRed();
        int Gx_above = top.getGreen();
        int Bx_above = top.getBlue();

        int gradiantV_Rx = Rx_down - Rx_above;
        int gradiantV_Gx = Gx_down - Gx_above;
        int gradiantV_Bx = Bx_down - Bx_above;

        int gradiantV_result = (gradiantV_Rx * gradiantV_Rx) + (gradiantV_Gx * gradiantV_Gx)
                + (gradiantV_Bx * gradiantV_Bx);

        double pixelEnergy = Math.sqrt((gradiant_result + gradiantV_result));

        return pixelEnergy;
    }

    public static void main(String[] args) {
        Picture pht = new Picture("D:\\ADS-2\\ADS-2_2019501110\\DAY -7\\seam\\7x3.png");
        SeamCarver sc = new SeamCarver(pht);

        // sc.removeHorizontalSeam(sc.findHorizontalSeam());
        // System.out.println(Arrays.toString(sc.findHorizontalSeam()));
        // sc.removeVerticalSeam(sc.findVerticalSeam());
        // System.out.println(sc.width());
        // System.out.println(sc.height());
        // System.out.println(Arrays.toString(sc.findHorizontalSeam()));
        // System.out.println(Arrays.toString(sc.findVerticalSeam()));
        // sc.removeHorizontalSeam(sc.findHorizontalSeam());
        // sc.removeVerticalSeam(sc.findVerticalSeam());

        // System.out.println(sc.width());
        // System.out.println(sc.height());

    }

}