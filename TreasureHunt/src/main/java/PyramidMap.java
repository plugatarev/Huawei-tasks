/**
 * Class represents pyramid map.
 * @param startWalls wall start points
 * @param endWalls wall end points
 * @param borderPoints points on the border of the pyramid
 * @param treasure treasure point of the pyramid
 */
public record PyramidMap(Point[] startWalls, Point[] endWalls, Point[] borderPoints, Point treasure) {
}
