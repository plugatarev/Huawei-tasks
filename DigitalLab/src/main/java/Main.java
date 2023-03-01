import java.util.List;

/**
 * Application launch class.
 */
public class Main {
    public static void main(String[] args) {
        MatrixReader matrixReader = new MatrixReader(System.in);

        Matrix pattern = matrixReader.readMatrix();
        Matrix matrix = matrixReader.readMatrix();

        if (matrix.height() < pattern.height() || matrix.width() < pattern.width()) {
            Printer.printOriginalMatrix(matrix);
            return;
        }

        List<Point> points = PatternFinder.findMatches(matrix, pattern);
        Printer.printMatrixWithReplacement(matrix, points);
    }
}
