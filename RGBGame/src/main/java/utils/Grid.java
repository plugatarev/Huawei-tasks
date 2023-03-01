package utils;

/**
 * Class for representing the playing field as a graph.
 */
public class Grid {
    private final Color[][] nodes;
    private final int height;
    private final int width;
    private static int COUNT;
    private final boolean[][] visited;
    private final boolean[][] result;

    /**
     * @param nodes playing field cell colors.
     * @param height playing field height.
     * @param width playing field width.
     */
    public Grid(Color[][] nodes, int height, int width) {
        this.nodes = nodes;
        this.height = height;
        this.width = width;
        visited = new boolean[height][width];
        result = new boolean[height][width];
    }

    /**
     * method looks for the largest single-color cluster.
     * @return two-dimensional boolean array where true indicates that the cell belongs to the cluster, false that does not belong.
     */
    public boolean[][] findLargestConnectedComponent() {
        int currentMax = Integer.MIN_VALUE;

        for (int j = 0; j < width; j++) {
            for (int i = height - 1; i >= 0; i--) {
                if (!nodes[i][j].equals(Color.EMPTY)) {
                    currentMax = calculateConnectedComponentFor(i, j, currentMax);
                }
            }
        }
        return result;
    }

    /**
     * marks the cells that belong to the cluster with cell.
     */
    public boolean[][] getConnectedComponentFor(Cell cell) {
        int i = cell.y();
        int j = cell.x();
        calculateConnectedComponentFor(i, j, Integer.MIN_VALUE);
        result[i][j] = true;
        return result;
    }

    private int calculateConnectedComponentFor(int y, int x, int currentMax) {
        resetVisited();
        COUNT = 0;

        // checking cell to the right
        if (x + 1 < width) {
            BFS(nodes[y][x], nodes[y][x + 1], y, x);
        }
        // updating result
        if (COUNT > currentMax) {
            currentMax = COUNT;
            resetResult(nodes[y][x]);
        }
        resetVisited();
        COUNT = 0;

        // checking cell downwards
        if (y + 1 < height) {
            BFS(nodes[y][x], nodes[y + 1][x], y, x);
        }

        // updating result
        if (COUNT > currentMax) {
            currentMax = COUNT;
            resetResult(nodes[y][x]);
        }
        return currentMax;
    }

    private void BFS(Color x, Color y, int i, int j) {
        if (!x.equals(y)) return;

        visited[i][j] = true;
        COUNT++;

        int[] xMove = { 0, 0, 1, -1 };
        int[] yMove = { 1, -1, 0, 0 };

        for (int u = 0; u < 4; u++)
            if ((isValid(i + yMove[u],j + xMove[u], x))) {
                BFS(x, y, i + yMove[u],j + xMove[u]);
            }
    }

    private void resetVisited() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                visited[i][j] = false;
            }
    }

    private void resetResult(Color key) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] && nodes[i][j].equals(key)) {
                    result[i][j] = visited[i][j];
                }
                else {
                    result[i][j] = false;
                }
            }
        }
    }

    private boolean isValid(int x, int y, Color key) {
        if (x >= height || y >= width || x < 0 || y < 0) return false;
        return !visited[x][y] && nodes[x][y].equals(key);
    }
}