import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PatternFinderTest {
    @Test
    public void test1DMatrix() {
        int dim = 1;
        int[][] m = {{10}};
        Matrix matrix = new Matrix(m, dim, dim);
        Matrix pattern = new Matrix(m, dim, dim);
        List<Point> res = PatternFinder.findMatches(matrix, pattern);

        Assert.assertEquals(res.size(), 1);
        Assert.assertEquals(res.get(0), new Point(0, 0));
    }

    @Test
    public void matrixSameNumbersAndPatternWithSameNumber() {
        int dim = 5;
        int[][] m = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = 10;
            }
        }
        Matrix matrix = new Matrix(m, dim, dim);
        int[][] p = {{10}};
        Matrix pattern = new Matrix(p, 1, 1);

        List<Point> res = PatternFinder.findMatches(matrix, pattern);

        Assert.assertEquals(res.size(), 25);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Assert.assertTrue(res.contains(new Point(i, j)));
            }
        }
    }

    @Test
    public void matrixSameNumbersAnd2DPatternWithSameNumber() {
        int dim = 5;
        int[][] m = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = 10;
            }
        }
        Matrix matrix = new Matrix(m, dim, dim);
        int[][] p = { {10, 10}, {10, 10}};
        Matrix pattern = new Matrix(p, 2, 2);

        List<Point> res = PatternFinder.findMatches(matrix, pattern);

        for (int i = 0; i < dim - 1; i++) {
            for (int j = 0; j < dim; j++) {
                Assert.assertTrue(res.contains(new Point(i, j)));
            }
        }
    }

    @Test
    public void noMatches() {
        int dim = 5;
        int[][] m = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = 10;
            }
        }
        Matrix matrix = new Matrix(m, dim, dim);
        int[][] p = {{10, 10}, {1, 10}};
        Matrix pattern = new Matrix(p, 2, 2);

        List<Point> res = PatternFinder.findMatches(matrix, pattern);

        Assert.assertEquals(res.size(), 0);
    }

}
