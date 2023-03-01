import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class IntegrationTest {

    @Test
    public void oneBlockOneSet() {
        String input = """
                5
                1 2 3 4 5
                0
                0""";

        String res = "YES\n";

        executeTest(input, res);
    }

    @Test
    public void oneBlockSeveralSets() {
        String input = """
                5
                1 2 3 4 5
                3 1 2 4 5
                5 4 3 2 1
                0
                0""";

        String res = """
                YES
                NO
                YES
                """;

        executeTest(input, res);
    }

    @Test
    public void severalBlocksWithOneSet() {
         String input = """
                5
                1 2 3 4 5
                0
                7
                7 6 5 4 3 2 1
                0
                0""";

        String res = """
                YES
                
                YES
                """;

        executeTest(input, res);
    }

    @Test
    public void incorrectData() {
        String input = "5\n1 2 3 f 5\n0\n0";
        setInput(input);
        Assert.assertThrows(NumberFormatException.class, () -> Main.main(null));
    }

    @Test
    public void incorrectCountCoach() {
        String input = "-1\n7 6 5 4 3 2 1\n0\n0";
        setInput(input);
        Assert.assertThrows(IllegalArgumentException.class, () -> Main.main(null));
    }

    @Test
    public void incorrectCoachesSet() {
        String input = "7\n7 6 8 4 3 2 1\n0\n0";
        setInput(input);
        Assert.assertThrows(IllegalArgumentException.class, () -> Main.main(null));
    }

    @Test
    public void countCoachesGreaterThanCoaches() {
        String input = "7\n7 6 5 4 3 2 1 10\n0\n0";
        setInput(input);
        Assert.assertThrows(IllegalArgumentException.class, () -> Main.main(null));
    }

    @Test
    public void repeatCoachesNumber() {
        String input = "7\n7 6 5 3 3 2 1\n0\n0";
        setInput(input);
        Assert.assertThrows(IllegalArgumentException.class, () -> Main.main(null));
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
