package model;

import utils.Cell;
import utils.Color;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Game class.
 */
public class Game {
    private static final int BONUS_SCORE = 1000;

    private final GameField field;
    private int lastScore;
    private int score;
    private Cell cellDeletedCluster;

    public Game(GameField field) {
        this.field = field;
    }

    /**
     * Removes the largest cluster in the playing field and updates game score.
     * @return count deleted cells.
     */
    public int update() {
        List<Cell> biggestCluster = field.getBiggestCluster();
        cellDeletedCluster = getSelectedCell(biggestCluster);
        int countDeletedCells = field.deleteCluster(biggestCluster);
        if (countDeletedCells > 1) {
            lastScore = (countDeletedCells - 2) * (countDeletedCells - 2);
            score += lastScore;
            if (getRemainingBallsCount() == 0) score += BONUS_SCORE;
        }
        return countDeletedCells;
    }

    /**
     * @return selected ball from last removed cluster.
     */
    public Cell getCellDeletedCluster() {
        return cellDeletedCluster;
    }

    /**
     * @return score after the last deleted cluster.
     */
    public int getLastScore() {
        return lastScore;
    }

    /**
     * @return overall score of the game.
     */
    public int getScore() {
        return score;
    }

    public long getRemainingBallsCount() {
        return Arrays.stream(field.field()).flatMap(Arrays::stream).filter(s -> s != Color.EMPTY).count();
    }

    public GameField getField() {
        return field;
    }

    private Cell getSelectedCell(List<Cell> cluster) {
        int minX = field.getWidth() + 1;
        for (Cell p : cluster) {
            if (p.x() < minX) {
                minX = p.x();
            }
        }

        int finalMinX = minX;
        return cluster.stream()
                      .filter(s -> s.x() == finalMinX)
                      .max(Comparator.comparingInt(Cell::y))
                      .orElse(null);
    }
}