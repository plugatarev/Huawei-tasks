import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class for reading pyramid map.
 */
public class MapReader {

    private static final int COUNT_FIXED_POINTS = 4;

    private final Scanner input;

    /**
     * @param inputStream stream which will be used for reading
     */
    public MapReader(InputStream inputStream) {
        input = new Scanner(inputStream);
    }

    /**
     * Read pyramid map.
     */
    public PyramidMap readPyramidMap() {
        int linesCount = input.nextInt();

        Point[] startWalls = new Point[linesCount];
        Point[] endWalls = new Point[linesCount];
        Point[] borderPoints = new Point[2 * linesCount + COUNT_FIXED_POINTS];

        for (int i = 0; i < linesCount; i++) {
            startWalls[i] = new Point(input.nextInt(), input.nextInt());
            endWalls[i] = new Point(input.nextInt(), input.nextInt());
            borderPoints[2 * i] = startWalls[i];
            borderPoints[2 * i + 1] = endWalls[i];
        }

        borderPoints[2 * linesCount] = new Point(0, 0);
        borderPoints[2 * linesCount + 1] = new Point(0, 100);
        borderPoints[2 * linesCount + 2] = new Point(100, 0);
        borderPoints[2 * linesCount + 3] = new Point(100, 100);

        Arrays.sort(borderPoints, PointOperation::compare);
        Point treasure = new Point(input.nextDouble(), input.nextDouble());

        return new PyramidMap(startWalls, endWalls, borderPoints, treasure);
    }
}
