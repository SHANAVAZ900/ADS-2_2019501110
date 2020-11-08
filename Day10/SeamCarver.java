package Day10;

import java.awt.Color;

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

    public int[] findHorizontalSeam() {

        int[] seam = new int[pixelEnergy[0].length];

        if (pixelEnergy.length == 1) {
            for (int i = 0; i < width(); i++) {
                seam[i] = 0;
            }
            return seam;
        }

        if (pixelEnergy[0].length == 1) {
            seam[0] = 0;
            return seam;
        }

        double[][] temp = new double[pixelEnergy[0].length][pixelEnergy.length];
        for (int col = 0; col < pixelEnergy[0].length; col++) {
            for (int row = 0; row < pixelEnergy.length; row++) {
                temp[col][row] = pixelEnergy[row][col];
            }

        }

        double[][] Vpixel = temp;

        for (int i = 1; i < Vpixel.length; i++) {
            for (int j = 0; j < Vpixel[0].length; j++) {
                double first_min, second_min;

                if (j == 0) {
                    Vpixel[i][j] += Math.min(Vpixel[i - 1][j], Vpixel[i - 1][j + 1]);
                } else if (j == Vpixel[0].length - 1) {
                    Vpixel[i][j] += Math.min(Vpixel[i - 1][j], Vpixel[i - 1][j - 1]);
                } else {
                    first_min = Math.min(Vpixel[i - 1][j], Vpixel[i - 1][j - 1]);
                    second_min = Math.min(first_min, Vpixel[i - 1][j + 1]);
                    Vpixel[i][j] = Vpixel[i][j] + second_min;

                }
            }
        }

        double min = Double.MAX_VALUE;
        int pointer = 0;

        for (int i = 0; i < Vpixel[0].length; i++) {
            if (min > Vpixel[Vpixel.length - 1][i]) {
                min = Vpixel[Vpixel.length - 1][i];
                pointer = i;
            }

        }

        for (int i = Vpixel.length - 1; i > 0; i--) {
            seam[i] = pointer;

            if (pointer == 0) {
                if (Vpixel[i - 1][pointer] > Vpixel[i - 1][pointer + 1]) {
                    pointer++;
                }
            } else if (pointer == Vpixel[0].length - 1) {
                if (Vpixel[i - 1][pointer] > Vpixel[i - 1][pointer - 1]) {
                    pointer--;
                }
            } else {
                if ((Vpixel[i - 1][pointer - 1] <= Vpixel[i - 1][pointer])
                        && Vpixel[i - 1][pointer - 1] <= (Vpixel[i - 1][pointer + 1])) {
                    pointer--;

                } else if (Vpixel[i - 1][pointer] > Vpixel[i - 1][pointer + 1]) {
                    pointer++;

                }

            }

        }
        seam[0] = pointer;
        return seam;
    }

    public int[] findVerticalSeam() {

        double[][] Vpixel = pixelEnergy;
        int[] seam = new int[Vpixel.length];

        if (Vpixel.length == 1) {
            for (int i = 0; i < height(); i++) {
                seam[i] = 0;
            }
            return seam;
        }

        if (Vpixel[0].length == 1) {
            for (int i = 0; i < width(); i++) {
                seam[i] = 0;
            }
            return seam;
        }

        for (int i = 1; i < Vpixel.length; i++) {
            for (int j = 0; j < Vpixel[0].length; j++) {
                double first_min, second_min;

                if (j == 0) {
                    Vpixel[i][j] += Math.min(Vpixel[i - 1][j], Vpixel[i - 1][j + 1]);
                } else if (j == Vpixel[0].length - 1) {
                    Vpixel[i][j] += Math.min(Vpixel[i - 1][j], Vpixel[i - 1][j - 1]);
                } else {
                    first_min = Math.min(Vpixel[i - 1][j], Vpixel[i - 1][j - 1]);
                    second_min = Math.min(first_min, Vpixel[i - 1][j + 1]);
                    Vpixel[i][j] = Vpixel[i][j] + second_min;

                }
            }
        }

        double min = Double.MAX_VALUE;
        int pointer = 0;

        for (int i = 0; i < Vpixel[0].length; i++) {
            if (min > Vpixel[Vpixel.length - 1][i]) {
                min = Vpixel[Vpixel.length - 1][i];
                pointer = i;
            }

        }

        for (int i = Vpixel.length - 1; i > 0; i--) {
            seam[i] = pointer;

            if (pointer == 0) {
                if (Vpixel[i - 1][pointer] > Vpixel[i - 1][pointer + 1]) {
                    pointer++;
                }
            } else if (pointer == Vpixel[0].length - 1) {
                if (Vpixel[i - 1][pointer] > Vpixel[i - 1][pointer - 1]) {
                    pointer--;

                }
            } else {

                if ((Vpixel[i - 1][pointer - 1] <= Vpixel[i - 1][pointer])
                        && Vpixel[i - 1][pointer - 1] <= (Vpixel[i - 1][pointer + 1])) {
                    pointer--;

                } else if (Vpixel[i - 1][pointer] > Vpixel[i - 1][pointer + 1]) {
                    pointer++;

                }

            }

        }
        seam[0] = pointer;
        return seam;
    }

    public void removeHorizontalSeam(int[] seam) {

        if (seam == null || height() <= 1 || seam.length != width() || seam[0] < 0 || seam[0] > height() - 1) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] < 0 || seam[i + 1] < 0 || seam[i] > height() - 1 || seam[i + 1] > height() - 1) {
                throw new IllegalArgumentException();
            }
            if (Math.abs(seam[i + 1] - seam[i]) > 1) {
                throw new IllegalArgumentException();
            }
        }

        Picture pic = new Picture(width(), height() - 1);

        for (int row = 0; row < width(); row++) {
            int index = seam[row];
            int temp = 0;

            for (int col = 0; col < height() - 1; col++) {
                if (col == index) {
                    temp = 1;
                }
                if (temp == 0) {
                    Color color = pht.get(row, col);
                    pic.set(row, col, color);
                } else {
                    Color color = pht.get(row, col + 1);
                    pic.set(row, col, color);
                }
            }
        }

        pixelEnergy = new double[height() - 1][width()];
        for (int i = 0; i < width(); i++) {

            for (int j = 0; j < height() - 1; j++) {
                pixelEnergy[j][i] = energy(i, j);
            }
        }
        pht = pic;
    }

    // public static void main(String[] args) {
    // Picture pht = new Picture("D:\\ADS-2\\ADS-2_2019501110\\DAY
    // -7\\seam\\6x5.png");
    // SeamCarver sc = new SeamCarver(pht);
    // sc.removeHorizontalSeam(sc.findHorizontalSeam());
    // sc.removeVerticalSeam(sc.findVerticalSeam());

    // System.out.println(sc.width());
    // System.out.println(sc.height());

    // }

}
