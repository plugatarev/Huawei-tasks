import org.junit.Assert;
import org.junit.Test;
import utils.Color;
import utils.Grid;
import utils.Cell;

import java.util.ArrayList;
import java.util.List;

public class GridTest {

    @Test
    public void testFindLargestConnectedComponent() {
        Color[][] f = {{Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED},
                    {Color.EMPTY, Color.RED, Color.RED, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED, Color.RED},
                    {Color.GREEN, Color.EMPTY, Color.RED, Color.RED, Color.EMPTY, Color.GREEN, Color.EMPTY, Color.RED},
                    {Color.GREEN, Color.GREEN, Color.EMPTY, Color.RED, Color.EMPTY,Color.EMPTY, Color.EMPTY, Color.EMPTY},
                    {Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE,Color.BLUE, Color.BLUE},
                    {Color.RED, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE}};
        Grid g = new Grid(f, 6, 8);
        List<Cell> points = new ArrayList<>();
        points.add(new Cell(1, 1, null));
        points.add(new Cell(1, 2, null));
        points.add(new Cell(2, 2, null));
        points.add(new Cell(2, 3, null));
        points.add(new Cell(3, 3, null));
        points.add(new Cell(4, 3, null));
        points.add(new Cell(4, 4, null));
        points.add(new Cell(5, 3, null));
        points.add(new Cell(5, 4, null));
        boolean[][] r = g.findLargestConnectedComponent();
        Assert.assertTrue(isIncludeComponent(points, r, 6, 8));
    }

    @Test
    public void test2FindLargestConnectedComponent() {
        Color[][] f = {{Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.RED, Color.RED},
                {Color.EMPTY, Color.RED, Color.RED, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED, Color.RED},
                {Color.GREEN, Color.EMPTY, Color.BLUE, Color.RED, Color.EMPTY, Color.GREEN, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.GREEN, Color.EMPTY, Color.GREEN, Color.EMPTY,Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.BLUE, Color.BLUE,Color.GREEN, Color.BLUE},
                {Color.RED, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.RED}};
        Grid g = new Grid(f, 6, 8);
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 1, null));
        cells.add(new Cell(0, 2, null));
        cells.add(new Cell(0, 3, null));
        cells.add(new Cell(0, 4, null));
        cells.add(new Cell(1, 3, null));
        boolean[][] r = g.findLargestConnectedComponent();
        Assert.assertTrue(isIncludeComponent(cells, r, 6, 8));
    }

    @Test
    public void testGetConnectedComponent() {
        Color[][] f = {{Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.RED, Color.RED},
                {Color.EMPTY, Color.RED, Color.RED, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED, Color.RED},
                {Color.GREEN, Color.EMPTY, Color.BLUE, Color.RED, Color.EMPTY, Color.GREEN, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.GREEN, Color.EMPTY, Color.GREEN, Color.EMPTY,Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.BLUE, Color.BLUE,Color.GREEN, Color.BLUE},
                {Color.RED, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.RED}};
        Grid g = new Grid(f, 6, 8);
        boolean[][] r = g.getConnectedComponentFor(new Cell(0, 0, null));
        Assert.assertEquals(getSizeComponent(r, 6, 8), 1);
    }

    @Test
    public void test2GetConnectedComponent() {
        Color[][] f = {{Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.RED, Color.RED},
                {Color.EMPTY, Color.RED, Color.RED, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED, Color.RED},
                {Color.GREEN, Color.EMPTY, Color.BLUE, Color.RED, Color.EMPTY, Color.GREEN, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.GREEN, Color.EMPTY, Color.GREEN, Color.EMPTY,Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.BLUE, Color.BLUE,Color.GREEN, Color.BLUE},
                {Color.RED, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.RED}};
        Grid g = new Grid(f, 6, 8);
        boolean[][] r = g.getConnectedComponentFor(new Cell(0, 1, null));
        Assert.assertEquals(getSizeComponent(r, 6, 8), 5);
    }

    private boolean isIncludeComponent(List<Cell> points, boolean[][] r, int height, int width) {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (r[i][j]){
                    count++;
                    if (!points.contains(new Cell(i, j, null))) return false;
                }
            }
        }
        return points.size() == count;
    }

    private int getSizeComponent(boolean[][] r, int height, int width) {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (r[i][j]){
                    count++;
                }
            }
        }
        return count;
    }
}
