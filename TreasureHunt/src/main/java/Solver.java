/**
 * Class for finding the minimum number of doors leading to a treasure
 */
public class Solver {
    /**
     * Finds the minimum number of doors leading to a treasure
     * @return minimal count of doors to treasure
     */
    public static int findMinimalCountOfDoorsToTreasure(PyramidMap pyramidMap) {
        int min = Integer.MAX_VALUE;
        int borderPointsCount = pyramidMap.borderPoints().length;
        int linesCount = pyramidMap.startWalls().length;
        for (int i = 0; i < borderPointsCount; i++) {
            Point midpoint = PointOperation.getMidpoint(
                    pyramidMap.borderPoints()[i], pyramidMap.borderPoints()[(i + 1) % borderPointsCount]
            );

            int res = 1;
            for (int j = 0; j < linesCount; j++) {
                if (areCrossing(pyramidMap.treasure(), midpoint, pyramidMap.startWalls()[j], pyramidMap.endWalls()[j])) {
                    res++;
                }
            }
            min = Math.min(min, res);
        }
        return min;
    }

    private static boolean areCrossing(Point p1, Point p2, Point p3, Point p4) {
        //The relative position of the segments can be checked using vector products
        double d1 = PointOperation.mul(PointOperation.sub(p2, p1), PointOperation.sub(p3, p1));
        double d2 = PointOperation.mul(PointOperation.sub(p2, p1), PointOperation.sub(p4, p1));
        double d3 = PointOperation.mul(PointOperation.sub(p4, p3), PointOperation.sub(p1, p3));
        double d4 = PointOperation.mul(PointOperation.sub(p4, p3), PointOperation.sub(p2, p3));

        double v1 = PointOperation.sign(d1);
        double v2 = PointOperation.sign(d2);
        double v3 = PointOperation.sign(d3);
        double v4 = PointOperation.sign(d4);

        //vector collinear
        if (v1 == 0 && v2 == 0 && v3 == 0 && v4 == 0) {
            return false;
        }
        //vector intersection condition
        return v1 * v2 <= 0 && v3 * v4 <= 0;
    }
}
