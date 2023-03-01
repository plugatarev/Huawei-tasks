/**
 * Stores descriptive information about the pizzeria
 * @param northBlocks number of blocks on which the pizzeria is located to the north
 * @param eastBlocks number of blocks on which the pizzeria is located to the east
 * @param southBlocks number of blocks on which the pizzeria is located to the south
 * @param westBlocks number of blocks on which the pizzeria is located to the west
 */
public record Result(int northBlocks, int eastBlocks, int southBlocks, int westBlocks) {
    @Override
    public String toString() {
        return northBlocks + " " + eastBlocks + " " + southBlocks + " " + westBlocks;
    }
}
