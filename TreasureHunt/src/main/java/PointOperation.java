/**
 * Сlass over point operations.
 */
public class PointOperation {
    private static final double EPS = 1e-9;

    /**
     * @return difference of points p1 and p2.
     */
    public static Point sub(Point p1, Point p2) {
        return new Point(p1.x() - p2.x(), p1.y() - p2.y());
    }

    /**
     * @return product based on the cross product with vectors at the ends of the points p1 and p2.
     */
    public static double mul(Point p1, Point p2) {
        return p1.x() * p2.y() - p1.y() * p2.x();
    }


    /**
     * @return sign of p.
     */
    public static int sign(double p) {
        if (p > EPS) return 1;
        if (p < -EPS) return -1;
        return 0;
    }

    /**
     * @return vector-based comparison with vectors at end points з1.
     */
    public static int compare(Point p1, Point p2) {
        return sign(Math.atan2(p1.y() - 50.0, p1.x() - 50) - Math.atan2(p2.y() - 50, p2.x() - 50));
    }

    /**
     * @return midpoint of the segment represented by points p1 and p2.
     */
    public static Point getMidpoint(Point p1, Point p2) {
        return new Point((p1.x() + p2.x()) / 2, (p1.y() + p2.y()) / 2);
    }
}
