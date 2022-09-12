package model;

import utils.Color;
import utils.Grid;
import utils.Point;

import javax.security.auth.login.AccountLockedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public record GameField(Color[][] field) {
    private static final int HEIGHT = 6;
    private static final int WIDTH = 8;

    public static GameField createGameField(Scanner input) {
        Color[][] field = new Color[HEIGHT][WIDTH];
        for (int j = 0; j < HEIGHT; j++) {
            for (int k = 0; k < WIDTH; k++) {
                field[j][k] = getNextBall(input);
            }
        }
        return new GameField(field);
    }

    private static Color getNextBall(Scanner input) {
        return switch (input.next().charAt(0)) {
            case 'R' -> Color.RED;
            case 'G' -> Color.GREEN;
            case 'B' -> Color.BLUE;
            default -> throw new IllegalStateException("Unexpected value: " + input.next().charAt(0));
        };
    }

    public int getEmptyPoint() {
        return 0;
        //TODO
    }

    public List<Point> getBiggestCluster() {
        Grid grid = new Grid(field, HEIGHT, WIDTH);
        return getPointCluster(grid.findLargestConnectedComponent());
    }

    private List<Point> getPointCluster(boolean[][] result) {
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (result[i][j]) res.add(new Point(HEIGHT - i - 1, j, getColor(HEIGHT - i - 1, j)));
            }
        }
        return res;
    }

//    private int deleteCluster(Point biggestClusterPoint) {
//        List<Coords> cluster = getCluster(i, j);
//        if (cluster.size() < 2) {
//            return 0;
//        }
//
//        for (Coords coords : cluster) {
//            setColor(coords.getX(), coords.getY(), Color.Empty);
//        }
//        fit();
//
//        return cluster.size();
//    }

    private Color getColor(int x, int y) {
        return field[HEIGHT - 1 - y][x];
    }

    private List<Point> getCluster(Point startPoint) {
        List<Point> cluster = new ArrayList<>();
        Grid grid = new Grid(field, HEIGHT, WIDTH);
        boolean[][] component = grid.getConnectedComponentFor(startPoint);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (component[i][j]) cluster.add(new Point(HEIGHT - i - 1, j, getColor(HEIGHT - i - 1, j)));
            }
        }
    }

//    private void tryExtendCluster(Point point, List<Point> cluster) {
//        int x = point.x();
//        int y = point.y();
//        if (isBelongCluster(point) && !cluster.contains(point)) {
//            cluster.add(point);
//            tryExtendCluster(new Point(x, y + 1, getColor(x,y + 1)), cluster);
//            tryExtendCluster(new Point(x, y - 1, getColor(x,y - 1)), cluster);
//            tryExtendCluster(new Point(x + 1, y, getColor(x + 1, y)), cluster);
//            tryExtendCluster(new Point(x - 1, y, getColor(x - 1, y)), cluster);
//        }
//    }

    private boolean isBelongCluster(Point point) {
        int x = point.x();
        int y = point.y();
        return point.color() != Color.EMPTY && areIndexesValid(x, y) && getColor(x, y) == point.color();
    }

    private boolean areIndexesValid(int i, int j) {
        return i >= 0 && i < WIDTH &&
                j >= 0 && j < HEIGHT;
    }

    public static void main(String[] args) {
        Color[][] f = {{Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED},
                {Color.EMPTY, Color.RED, Color.RED, Color.BLUE, Color.GREEN, Color.GREEN, Color.RED, Color.RED},
                {Color.GREEN, Color.EMPTY, Color.RED, Color.RED, Color.EMPTY, Color.GREEN, Color.EMPTY, Color.RED},
                {Color.GREEN, Color.GREEN, Color.EMPTY, Color.RED, Color.EMPTY,Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE,Color.BLUE, Color.BLUE},
                {Color.RED, Color.RED, Color.GREEN, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE}};
//        GameField gf = new GameField(f);
//        List<Point> l = gf.getBiggestCluster();
        Grid g = new Grid(f, HEIGHT, WIDTH);
        boolean[][] r = g.getConnectedComponentFor(1, 1);
        System.out.println("he");
    }
}
