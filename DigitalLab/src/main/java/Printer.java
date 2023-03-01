import java.util.List;

/**
 * Console printer.
 */
public class Printer {

    /**
     * Prints the matrix in its original form.
     */
    public static void printOriginalMatrix(Matrix matrix) {
        int[][] rawData = matrix.matrix();
        for (int i = 0; i < matrix.height(); i++) {
            for (int j = 0; j < matrix.width(); j++) {
                System.out.print(rawData[i][j]);
                if (j < matrix.width() - 1) System.out.print(" ");
            }
            if (i < matrix.height() - 1) System.out.println();
        }
    }

    /**
     * Prints a matrix replacing the matrix values at the points.
     */
    public static void printMatrixWithReplacement(Matrix matrix, List<Point> points) {
        int[][] rawData = matrix.matrix();
        for (int i = 0; i < matrix.height(); i++) {
            for (int j = 0; j < matrix.width(); j++) {
                if (points.contains(new Point(j, i))) {
                    if (rawData[i][j] == 0) System.out.print("*");
                    else System.out.print("2");
                }
                else System.out.print(rawData[i][j]);

                if (j < matrix.width() - 1) System.out.print(" ");
            }
            if (i < matrix.height() - 1) System.out.println();
        }
    }
}
