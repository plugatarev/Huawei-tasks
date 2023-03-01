import org.junit.Assert;
import org.junit.Test;

public class StringParserTest {
    @Test
    public void correctLine() {
        String line = "1 2 3 4 5";
        Integer[] digits = StringParser.parseDigits(line);

        Assert.assertEquals(digits.length, 5);
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals((int)digits[i], i + 1);
        }
    }

    @Test
    public void lineWithEndl() {
        String line = "1\n2\n3\n4\n5";
        Assert.assertThrows(NumberFormatException.class, () -> StringParser.parseDigits(line));
    }

    @Test
    public void reverseOrder() {
        String line = "6 5 4 3 2 1";
        Integer[] digits = StringParser.parseDigits(line);

        Assert.assertEquals(digits.length, 6);
        for (int i = 0; i < 6; i++) {
            Assert.assertEquals((int)digits[i], 6 - i);
        }
    }

    @Test
    public void characterInLine() {
        Assert.assertThrows(NumberFormatException.class, () -> StringParser.parseDigits("6 5 f 3 2 1"));
    }

    @Test
    public void symbolInLine() {
        Assert.assertThrows(NumberFormatException.class, () -> StringParser.parseDigits("6 5 2 3 , 1"));
    }

    @Test
    public void emojiInLine() {
        Assert.assertThrows(NumberFormatException.class, () -> StringParser.parseDigits("6 5 3 \uD83E\uDD6A 1"));
    }

    @Test
    public void emptyLine() {
        Assert.assertThrows(NumberFormatException.class, () -> StringParser.parseDigits(""));
    }

    @Test
    public void parseDigitCorrect() {
        String line = "6";
        int digit = StringParser.parseDigit(line);

        Assert.assertEquals(digit, 6);
    }

    @Test
    public void parseDigitSeveralDigits() {
        Assert.assertThrows(IllegalArgumentException.class, () -> StringParser.parseDigit("1 2 3 4"));
    }

    @Test
    public void parseDigitSymbol() {
        Assert.assertThrows(NumberFormatException.class, () -> StringParser.parseDigit("s"));
    }
}
