public class Main {
    public static void main(String[] args) {
        int[] s = new int[16];
        for (int i = 5; i < 21; i++) {
            s[i - 5] = 25 - i;
        }

        double[] x = new double[17];

        for (int i = 0; i < 17; i++) {
            x[i] = Math.random() * (17.0) - 7.0;
        }

        double[][] l = new double[16][17];

        for (int i = 0; i<16; i++) {
            for (int j = 0; j<17; j++) {
                l[i][j]=clc(s[i],x[j]);
            }
        }

        prnt(l);
    }

    public static double clc(int si, double xj) {
        if (si == 13) {
            return Math.sin(Math.cbrt(Math.log(Math.abs(xj))));
        } else if (si == 5 || si == 8 || si == 11 || si == 12 || si == 14 || si == 16 || si == 17 || si == 18) {
            return Math.pow((Math.pow(Math.E, Math.tan(xj))), 3 / Math.cbrt(Math.pow(xj, 0.25 * xj)));
        } else {
            return 0.25 / Math.pow(Math.log(Math.pow((Math.abs(xj) + 1) / Math.abs(xj), xj) / 2), Math.log(Math.pow(Math.abs(xj) / 2, 2)));
        }
    }

    public static void prnt(double[][] m) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 17; j++) {
                if (Double.isNaN(m[i][j])) {
                    System.out.printf("%12c", 'N');
                } else {
                    System.out.printf("%12.2f", m[i][j]);
                }
            }
            System.out.println();
        }
    }
}
