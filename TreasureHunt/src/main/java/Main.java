public class Main {
    public static void main(String[] args) {
        MapReader reader = new MapReader(System.in);

        PyramidMap map = reader.readPyramidMap();
        int minCountDoors = Solver.findMinimalCountOfDoorsToTreasure(map);

        System.out.println(minCountDoors);
    }
}
