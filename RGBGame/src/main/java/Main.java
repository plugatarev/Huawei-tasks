import controller.Controller;
import model.Game;
import model.GameField;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfGame = input.nextInt();
        for (int i = 1; i <= numberOfGame; i++) {
            GameField gameField = GameField.createGameField(input);
            System.out.printf("Game %d:%n", i);
            new Controller(new Game(gameField)).run();
        }
    }
}
