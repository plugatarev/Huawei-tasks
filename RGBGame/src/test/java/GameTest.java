import model.Game;
import model.GameField;
import org.junit.Assert;
import org.junit.Test;
import utils.Cell;
import utils.Color;

public class GameTest {

    @Test
    public void bonusGameWithAllBallsRemoved() {
        Color[][] field = {
                {Color.RED, Color.BLUE, Color.BLUE},
                {Color.RED, Color.BLUE, Color.BLUE},
                {Color.RED, Color.BLUE, Color.BLUE}
        };
        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 6);
        Assert.assertEquals(game.getLastScore(), 16);
        Assert.assertEquals(game.getRemainingBallsCount(), 3);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 1, Color.BLUE));

        countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getScore(), 16 + 1 + 1000);
        Assert.assertEquals(game.getRemainingBallsCount(), 0);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.RED));

    }

    @Test
    public void gameWithNotAllBallsRemoved() {
        Color[][] field = {
                {Color.RED, Color.BLUE, Color.BLUE},
                {Color.EMPTY, Color.BLUE, Color.GREEN},
                {Color.RED, Color.BLUE, Color.BLUE}
        };
        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 5);
        Assert.assertEquals(game.getLastScore(), 9);
        Assert.assertEquals(game.getRemainingBallsCount(), 3);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 1, Color.BLUE));

        countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 2);
        Assert.assertEquals(game.getLastScore(), 0);
        Assert.assertEquals(game.getRemainingBallsCount(), 1);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.RED));
    }

    @Test
    public void checkingShiftsAfterDeletion() {
        Color[][] field = {
                {Color.RED, Color.GREEN, Color.BLUE},
                {Color.EMPTY, Color.BLUE, Color.GREEN},
                {Color.GREEN, Color.BLUE, Color.BLUE}
        };
        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 5);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 1, Color.BLUE));

        countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 2);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.GREEN));
    }

    @Test
    public void checkingShiftsColumnsAfterDeletion() {
        Color[][] field = {
                {Color.RED, Color.GREEN, Color.RED},
                {Color.BLUE, Color.BLUE, Color.RED},
                {Color.BLUE, Color.BLUE, Color.RED}
        };
        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 4);
        Assert.assertEquals(game.getLastScore(), 4);
        Assert.assertEquals(game.getRemainingBallsCount(), 5);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.BLUE));

        countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 2);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 2, Color.RED));
    }

    @Test
    public void emptyField() {
        Color[][] field = {
                {Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.EMPTY, Color.EMPTY, Color.EMPTY},
                {Color.EMPTY, Color.EMPTY, Color.EMPTY}
        };

        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 0);
        Assert.assertEquals(game.getLastScore(), 0);
        Assert.assertEquals(game.getRemainingBallsCount(), 0);
        Assert.assertNull(game.getCellDeletedCluster());
    }

    @Test
    public void noClustersLargerThanOneDim() {
        Color[][] field = {
                {Color.GREEN, Color.RED, Color.BLUE},
                {Color.BLUE, Color.GREEN, Color.RED},
                {Color.RED, Color.BLUE, Color.GREEN}
        };

        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 0);
        Assert.assertEquals(game.getLastScore(), 0);
        Assert.assertEquals(game.getRemainingBallsCount(), 9);
        Assert.assertNull(game.getCellDeletedCluster());
    }

    @Test
    public void twoClustersSameSizeTest() {
        Color[][] field = {
                {Color.RED, Color.GREEN, Color.GREEN},
                {Color.RED, Color.GREEN, Color.BLUE},
                {Color.RED, Color.BLUE, Color.BLUE}
        };
        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 6);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.RED));

        countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 3);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.BLUE));

        countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 0);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.GREEN));
    }

    @Test
    public void removeLowerClusterWithTwoSameSizeClusters() {
        Color[][] field = {
                {Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.RED, Color.RED, Color.RED},
                {Color.GREEN, Color.GREEN, Color.GREEN}
        };

        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 6);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.GREEN));
    }

    @Test
    public void removeLeftmostClusterWithTwoSameSizeClusters() {
        Color[][] field = {
                {Color.BLUE, Color.RED, Color.GREEN},
                {Color.BLUE, Color.RED, Color.GREEN},
                {Color.BLUE, Color.RED, Color.GREEN}
        };

        Game game = createGame(field);

        int countDeletedCells = game.update();
        Assert.assertEquals(countDeletedCells, 3);
        Assert.assertEquals(game.getLastScore(), 1);
        Assert.assertEquals(game.getRemainingBallsCount(), 6);
        Assert.assertEquals(game.getCellDeletedCluster(), new Cell(2, 0, Color.BLUE));
    }

    private Game createGame(Color[][] field) {
        GameField gameField = new GameField(field, field.length, field[0].length);
        return new Game(gameField);
    }
}
