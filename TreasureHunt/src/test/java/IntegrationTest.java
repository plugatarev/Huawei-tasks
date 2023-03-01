import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class IntegrationTest {

    @Test
    public void exampleTest() {
        String line = """
                7
                20 0 37 100
                40 0 76 100
                85 0 0 75
                100 90 0 90
                0 71 100 61
                0 14 100 38
                100 47 47 100
                54,5 55,4""";

        executeTest(line, "2\n");
    }

    @Test
    public void treasureLocatedOrigin() {
        String line = """
                7
                20 0 37 100
                40 0 76 100
                85 0 0 75
                100 90 0 90
                0 71 100 61
                0 14 100 38
                100 47 47 100
                1 1""";

        executeTest(line, "1\n");
    }

    @Test
    public void treasureLocatedEnd() {
        String line = """
                7
                20 0 37 100
                40 0 76 100
                85 0 0 75
                100 90 0 90
                0 71 100 61
                0 14 100 38
                100 47 47 100
                99 99""";

        executeTest(line, "1\n");
    }

    @Test
    public void fourIdenticalWaysToGetToTreasure() {
        String line = """
                4
                10 0 10 100
                90 0 90 100
                0 10 100 10
                0 90 100 90
                50 50""";

        executeTest(line, "2\n");
    }

    @Test
    public void testMinimalDoorCount() {
        String line = """
                6
                10 0 10 100
                90 0 90 100
                0 10 100 10
                0 90 100 90
                40 0 40 100
                60 0 60 100
                50 50""";

        executeTest(line, "2\n");
    }

    @Test
    public void maxCountOfDoorsButOneSideOfTreasure() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < 31; i++) {
            stringBuilder.append(i).append(" 0 ").append(i).append(" 100\n");
        }
        stringBuilder.append("50 50");

        executeTest(stringBuilder.toString(), "1\n");

    }

    private void setInput(String line) {
        System.setIn(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)));
    }

    private void executeTest(String input, String expected) {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        setInput(input);
        Main.main(null);
        String standardOutput = myOut.toString();
        Assert.assertEquals(standardOutput, expected);
    }
}
