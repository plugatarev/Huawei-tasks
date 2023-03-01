/**
 * Class for parsing strings with digits.
 */
public class StringParser {
    /**
     * Returns integer array from space-separated line.
     * If line contains non-digits method throws NumberFormatException.
     */
    public static Integer[] parseDigits(String line) {
        String[] lineDigits = line.split(" ");
        int len = lineDigits.length;
        Integer[] digits = new Integer[len];
        for (int i = 0; i < len; i++) {
            digits[i] = Integer.parseInt(lineDigits[i]);
        }
        return digits;
    }

    /**
     * Returns integer from line.
     * If line contains more than one digit throws IllegalArgumentException.
     * If line contains non-digits method throws NumberFormatException.
     */
    public static Integer parseDigit(String line) {
        Integer[] digits = StringParser.parseDigits(line);
        if (digits.length != 1) {
            throw new IllegalArgumentException("Required one digit in a line");
        }
        return digits[0];
    }
}
