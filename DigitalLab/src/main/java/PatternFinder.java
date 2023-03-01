import java.util.ArrayList;
import java.util.List;

/**
 * Pattern finder class in a matrix.
 */
public class PatternFinder {
    private static final int MODULE = 701; //The modular value
    private static final int RADIX = 2; //radix

    private static int dr = 1;	 //Highest power for row hashing

    /**
     * Method that find for a pattern in a matrix based on Rabin-Karp algorithm for Pattern Searching in Matrix.
     * @return all points of the matrix that belong to the pattern.
     */
    public static List<Point> findMatches(Matrix matrix, Matrix pattern) {
        List<Point> result = new ArrayList<>();
        int matrixHeight = matrix.height();
        int matrixWidth = matrix.width();
        int patternHeight = pattern.height();
        int patternWidth = pattern.width();

        int[][] rawMatrix = matrix.matrix();
        int[][] rawPattern = pattern.matrix();

        dr = power(RADIX, patternHeight - 1, MODULE);
        int dc = power(RADIX, patternWidth - 1, MODULE); //Highest power for col hashing
        List<Integer> matrixHash = findHash(rawMatrix, patternHeight); //column hash for the matrix
        List<Integer> patternHash = findHash(rawPattern, patternHeight); //column hash for the pattern

        int patternVal = calculateMatrixHash(patternWidth, patternHash); //hash of entire pattern matrix

        for (int i = 0; i <= (matrixHeight - patternHeight); i++){
            int matrixVal = calculateMatrixHash(patternWidth, matrixHash);
            for (int j = 0; j <= (matrixWidth - patternWidth); j++) {
                if (patternVal == matrixVal) {
                    if (isMatch(rawMatrix, rawPattern, i, j)) {
                        addPoints(i, j, patternHeight, patternWidth, result);
                    }
                }
                //calculating t_val for next set of columns
                if (j < matrixWidth - patternWidth) {
                    matrixVal = (matrixVal % MODULE - ((matrixHash.get(j) % MODULE) * (dc % MODULE)) % MODULE + MODULE) % MODULE;
                    matrixVal = ((matrixVal % MODULE) * (RADIX % MODULE)) % MODULE;
                    matrixVal = (matrixVal % MODULE + matrixHash.get(j + patternWidth) % MODULE) % MODULE;
                }
            }
            if (i < matrixHeight - patternHeight) {
                //call this function for hashing form next row
                colRollingHash(rawMatrix, matrixHash, i, patternHeight);
            }
        }
        return result;
    }

    private static int calculateMatrixHash(int width, List<Integer> columnHash) {
        int matrixVal = 0;
        for (int j = 0; j < width; j++){
            matrixVal = ((matrixVal * RADIX) + columnHash.get(j)) % MODULE;
        }
        return matrixVal;
    }

    private static void addPoints(int y, int x, int patternHeight, int patternWidth, List<Point> result) {
        for (int i = y; i < y + patternHeight; i++) {
            for (int j = x; j < x + patternWidth; j++) {
                result.add(new Point(j, i));
            }
        }
    }

    private static int power(int a, int n, int m){
        if (n == 0) return 1;
        if (n == 1) return a % m;
        int pow = power(a,n / 2,m);
        if ((n & 1) == 1) {
            return ((a % m) * pow % m * pow % m) % m;
        }
        else {
            return ((pow % m) * (pow % m)) % m;
        }
    }

    //Checks if all values of pattern matches with the matrix
    private static boolean isMatch(int[][] matrix, int[][] pattern, int r, int c){
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if (pattern[i][j] != matrix[i+r][j+c]) return false;
            }
        }
        return true;
    }

    //Finds the first hash of first n rows where n is no. of rows in pattern
    private static List<Integer> findHash(int[][] matrix, int row){
        List<Integer> hash = new ArrayList<>();
        int col = matrix[0].length;
        for (int i = 0; i < col; i++) {
            int h = 0;
            for (int j = 0; j < row; j++) {
                h = ((h * RADIX) % MODULE + matrix[j][i] % MODULE) % MODULE;
            }
            hash.add(i, h);
        }
        return hash;
    }

    //rolling hash function for columns
    private static void colRollingHash(int[][] matrix, List<Integer> matrixHash, int row, int patternHeight) {
        for (int i = 0; i < matrixHash.size(); i++) {
            int tmp1 = (matrixHash.get(i) % MODULE - ((matrix[row][i]) % MODULE * (dr) % MODULE) % MODULE) % MODULE;
            int tmp2 = ((tmp1 % MODULE) * (RADIX % MODULE)) % MODULE;
            matrixHash.set(i, (tmp2 % MODULE + matrix[row + patternHeight][i] % MODULE) % MODULE);
        }
    }
}
