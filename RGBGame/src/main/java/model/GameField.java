package model;

import utils.Color;
import utils.Grid;
import utils.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing the playing field of the RGB game
 */
public record GameField(Color[][] field, int height, int width) {

    /**
     * Method for reading the playing field
     */
    public static Color[][] readField(Scanner input, int height, int width) {
        Color[][] field = new Color[height][width];
        for (int i = 0; i < height; i++) {
            String line = input.nextLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                field[i][j] = switch (c) {
                    case 'R' -> Color.RED;
                    case 'G' -> Color.GREEN;
                    case 'B' -> Color.BLUE;
                    default -> throw new IllegalStateException("Unexpected value: " + input.next().charAt(0));
                };
            }
        }
        return field;
    }

    /**
     * finds the largest cluster of the same color in the playing field
     * @return List of cells belonging to the largest cluster
     */
    public List<Cell> getBiggestCluster() {
        Grid grid = new Grid(field, height, width);
        return getPointCluster(grid.findLargestConnectedComponent());
    }

    /**
     * removes cells from the playing field
     * @param cluster cells to be removed
     * @return number of remote cells
     */
    public int deleteCluster(List<Cell> cluster) {
        if (cluster.size() < 2) {
            return 0;
        }
        for (Cell p : cluster) {
            int i = p.y();
            int j = p.x();
            field[i][j] = Color.EMPTY;
        }
        moveFieldDown();
        moveFieldLeft();
        return cluster.size();
    }

    /**
     * @return playing field width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return playing field height
     */
    public int getHeight() {
        return height;
    }

    private Color getColor(int y, int x) {
        return field[y][x];
    }

    private List<Cell> getPointCluster(boolean[][] result) {
        List<Cell> res = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (result[i][j]) {
                    res.add(new Cell(i, j, getColor(i, j)));
                }
            }
        }
        return res;
    }

    private void moveFieldDown() {
        for (int j = 0; j < width; j++) {
            List<Integer> emptyPoints = new ArrayList<>();
            for (int i = height - 1; i >= 0; i--) {
                if (field[i][j] == Color.EMPTY) emptyPoints.add(i);
                if (field[i][j] != Color.EMPTY && !emptyPoints.isEmpty()) {
                    int t = emptyPoints.get(0);
                    emptyPoints.remove(0);
                    field[t][j] = field[i][j];
                    field[i][j] = Color.EMPTY;
                    emptyPoints.add(i);
                }
            }
        }
    }

    private void moveFieldLeft() {
        List<Integer> emptyColumn = new ArrayList<>();
        for (int j = 0; j < width; j++) {
            boolean isEmptyColumn = true;
            for (int i = 0; i < height; i++) {
                if (field[i][j] != Color.EMPTY) {
                    isEmptyColumn = false;
                    break;
                }
                if (i == (height - 1)) {
                    emptyColumn.add(j);
                }
            }
            if (!isEmptyColumn && !emptyColumn.isEmpty()) {
                swapFieldColumn(j, emptyColumn.get(0));
                emptyColumn.add(j);
                emptyColumn.remove(0);
            }
        }
    }

    private void swapFieldColumn(int i, int j) {
        for (int k = 0; k < height; k++) {
            Color tmp = field[k][j];
            field[k][j] = field[k][i];
            field[k][i] = tmp;
        }
    }
}
