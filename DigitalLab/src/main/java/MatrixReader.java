import java.io.InputStream;
import java.util.Scanner;

/**
 * Class for reading a matrix.
 */
public class MatrixReader {
    private final Scanner input;

    public MatrixReader(InputStream inputStream) {
        this.input = new Scanner(inputStream);
    }

    /**
     * Reads the matrix.
     * @return read matrix.
     */
    public Matrix readMatrix() {
        int height = readDimension();
        int width = readDimension();
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = input.nextInt();
            }
        }
        return new Matrix(matrix, height, width);
    }

    private int readDimension() {
        return input.nextInt();
    }
}
