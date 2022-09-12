package controller;

import model.Game;

public record Controller(Game game) {
    public void run() {
        boolean isEnded = false;
        do{
            isEnded = game.step();
        } while(!isEnded);
    }
}
