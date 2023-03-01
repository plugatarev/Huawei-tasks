import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class IntegrationTest {

    @Test
    public void city2D() {
        String input = """
                2 2 2
                1 1 1
                2 2 1
                0""";

        String res = """
                Case 1:
                0 1 0 0
                0 0 0 1
                
                """;

        executeTest(input, res);
    }

    //Changed the order of the output values from the example
    //output format: north(up), east(right), south(down), west(left)
    @Test
    public void exampleTest() {
        String input = """
                5 5 6
                1 3 2
                2 1 4
                2 5 4
                3 3 5
                5 2 2
                5 4 2
                0""";

        String res = """
                Case 1:
                1 0 1 0
                0 2 1 1
                1 2 0 1
                1 2 1 1
                1 0 0 1
                0 0 1 1
                
                """;

        executeTest(input, res);
    }

    private void setInput(String line) {
        System.setIn(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)));
    }

    private void executeTest(String inputData, String expectedResult) {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        setInput(inputData);
        Main.main(null);
        String standardOutput = myOut.toString();
        Assert.assertEquals(standardOutput, expectedResult);
    }
}
