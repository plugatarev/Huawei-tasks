package controller;

import model.Game;
import utils.Cell;
import utils.Color;

/**
 * Controller class that controls the game.
 */
public record Controller(Game game) {
    /**
     * method starts the game.
     */
    public void run() {
        int gameMove = 1;

        while (true) {
            int removedBalls = game.update();
            Cell lastCell = game.getCellDeletedCluster();
            if (lastCell == null) break;

            char color = colorToSymbol(lastCell.color());

            System.out.printf("Move %d at (%d, %d): removed %d balls of color %c, got %d points\n",
                    gameMove++, game.getField().getHeight() - lastCell.y(), lastCell.x() + 1,
                    removedBalls, color, game.getLastScore());

        }
        long remainingBalls = game.getRemainingBallsCount();
        System.out.printf("Final score: %d, with %d balls remaining.\n\n", game.getScore(), remainingBalls);
    }

    private char colorToSymbol(Color color) {
        return switch (color) {
            case RED -> 'R';
            case GREEN -> 'G';
            case BLUE -> 'B';
            case EMPTY -> 'E';
        };
    }
}
