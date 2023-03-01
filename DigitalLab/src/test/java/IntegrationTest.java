import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class IntegrationTest {
    @Test
    public void threeMatch() {
        String input = """
                2 2
                1 0
                1 1
                5 5
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1""";

        String res = """
                1 2 * 0 0
                0 2 2 0 0
                2 * 0 1 0
                2 2 1 2 *
                0 0 1 2 2""";

        executeTest(input, res);
    }

    @Test
    public void pattern1D() {
        String input = """
                1 1
                1
                5 5
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1
                """;
        String res = """
                2 2 0 0 0
                0 2 2 0 0
                2 0 0 2 0
                2 2 2 2 0
                0 0 2 2 2""";

        executeTest(input, res);
    }

    @Test
    public void pattern1DVariant2() {
        String input = """
                1 1
                0
                5 5
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1""";

        String res = """
                1 1 * * *
                * 1 1 * *
                1 * * 1 *
                1 1 1 1 *
                * * 1 1 1""";

        executeTest(input, res);
    }

    @Test
    public void patternBiggerMatrix() {
        String input = """
                2 6
                1 0 0 1 0 1
                1 1 1 0 1 0
                5 5
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1""";

        String res = """
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1""";

        executeTest(input, res);
    }

    @Test
    public void patternEqualsMatrix() {
        String input = """
                5 5
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1
                5 5
                1 1 0 0 0
                0 1 1 0 0
                1 0 0 1 0
                1 1 1 1 0
                0 0 1 1 1""";

        String res = """
                2 2 * * *
                * 2 2 * *
                2 * * 2 *
                2 2 2 2 *
                * * 2 2 2""";

        executeTest(input, res);
    }

    @Test
    public void patternAndMatrix1D() {
        String input = """
                1 1
                1
                1 1
                1
                """;

        String res = "2";

        executeTest(input, res);
    }

    @Test
    public void patternAndMatrix1DVariant2() {
        String input = """
                1 1
                0
                1 1
                1
                """;

        String res = "1";

        executeTest(input, res);
    }

    private void executeTest(String testData, String expectedResult) {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        setInput(testData);
        Main.main(null);
        String standardOutput = myOut.toString();
        Assert.assertEquals(standardOutput, expectedResult);
    }

    private void setInput(String line) {
        System.setIn(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)));
    }
}