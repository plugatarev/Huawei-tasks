import controller.Controller;
import model.Game;
import model.GameField;
import utils.Color;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static final int RGB_GAME_FIELD_HEIGHT = 10;
    private static final int RGB_GAME_FIELD_WIDTH = 15;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(Paths.get("in").toAbsolutePath());
        int gameCount = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= gameCount; i++) {
            Color[][] field = GameField.readField(input, RGB_GAME_FIELD_HEIGHT, RGB_GAME_FIELD_WIDTH);
            GameField gameField = new GameField(field, RGB_GAME_FIELD_HEIGHT, RGB_GAME_FIELD_WIDTH);
            System.out.printf("Game %d:%n", i);
            Game game = new Game(gameField);
            new Controller(game).run();
        }
    }
}
