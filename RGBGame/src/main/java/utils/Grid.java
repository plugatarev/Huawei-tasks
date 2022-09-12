package utils;

public class Grid {
    private final Color[][] field;
    private final int height;
    private final int width;
    private static int COUNT;
    private final boolean[][] visited;
    private final boolean[][] result;

    public Grid(Color[][] field, int height, int width) {
        this.field = field;
        this.height = height;
        this.width = width;
        visited = new boolean[height][width];
        result = new boolean[height][width];
    }

    public boolean[][] findLargestConnectedComponent() {
        int currentMax = Integer.MIN_VALUE;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                resetVisited();
                COUNT = 0;

                // checking cell to the right
                if (j + 1 < width) {
                    BFS(field[i][j], field[i][j + 1], i, j);
                }
                // updating result
                if (COUNT >= currentMax) {
                    currentMax = COUNT;
                    resetResult(field[i][j]);
                }
                resetVisited();
                COUNT = 0;

                // checking cell downwards
                if (i + 1 < height) {
                    BFS(field[i][j],field[i + 1][j], i, j);
                }

                // updating result
                if (COUNT >= currentMax) {
                    currentMax = COUNT;
                    resetResult(field[i][j]);
                }
            }
        }
        return result;
    }

    public boolean[][] getConnectedComponentFor(Point point) {
        int i = point.y();
        int j = point.x();
        resetVisited();
        COUNT = 0;

        // checking cell to the right
        if (j + 1 < width) {
            BFS(field[i][j], field[i][j + 1], i, j);
        }
        // updating result
        int currentMax = COUNT;
        resetResult(field[i][j]);
        resetVisited();
        COUNT = 0;

        // checking cell downwards
        if (i + 1 < height) {
            BFS(field[i][j],field[i + 1][j], i, j);
        }

        // updating result
        if (COUNT >= currentMax) {
            currentMax = COUNT;
            resetResult(field[i][j]);
        }
        result[i][j] = true;
        return result;
    }

    private void BFS(Color x, Color y, int i, int j) {
        if (!x.equals(y)) return;

        visited[i][j] = true;
        COUNT++;

        int[] x_move = { 0, 0, 1, -1 };
        int[] y_move = { 1, -1, 0, 0 };

        for (int u = 0; u < 4; u++)
            if ((isValid(i + y_move[u],j + x_move[u], x))) {
                BFS(x, y, i + y_move[u],j + x_move[u]);
            }
    }

    private void resetVisited() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                visited[i][j] = false;
    }

    private void resetResult(Color key) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] && field[i][j].equals(key)) {
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
        return !visited[x][y] && field[x][y].equals(key);
    }
}
