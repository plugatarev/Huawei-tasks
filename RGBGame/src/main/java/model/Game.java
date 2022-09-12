package model;

import utils.Color;
import utils.Point;

import java.util.List;

public class Game {
    private final GameField field;
    private int stepNumber;
    private int totalScore;
    private int lastStepScore;
    private Color lastStepColor;
    private Point lastStepCoords;
    private int lastStepBalls;

    public Game(GameField field) {
        this.field = field;
    }

    public boolean step() {
        return false;
    }
}
//        List<Point> biggestClusterPoint = field.getBiggestCluster();
//        if (biggestClusterPoint == null) {
//            return false;
//        }
//
//        int ballsDeleted = deleteCluster(biggestClusterPoint);
//        if (ballsDeleted < 2) {
//            return false;
//        }
//
//        stepNumber++;
//        lastStepCoords = biggestClusterPoint;
//        lastStepColor = biggestClusterPoint.color();
//        lastStepBalls = ballsDeleted;
//        lastStepScore = (ballsDeleted - 2) * (ballsDeleted - 2);
//        totalScore += lastStepScore;
//
//        if (field.getEmptyPoint() == 0) {
//            totalScore += 1000;
//        }
//        return true;
//    }
//        return true;
//}
