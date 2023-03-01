import java.util.*;

public class Main {
    private static final String POSITIVE = "YES";
    private static final String NEGATIVE = "NO";

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        //Read new block
        while (true) {
            int coachesCount = StringParser.parseDigit(input.nextLine());
            if (coachesCount == 0) break;

            //Read coaches of block
            while (true) {
                Integer[] digits = StringParser.parseDigits(input.nextLine());
                if (digits.length == 1 && digits[0] == 0) {     // block end
                    result.add("");
                    break;
                }
                if (digits.length != coachesCount) {
                    throw new IllegalArgumentException("Incorrect coaches count");
                }
                validate(coachesCount, digits);

                Deque<Integer> requiredCoachesOrder = new ArrayDeque<>(Arrays.asList(digits));
                boolean isReorganized = Reorganizer.tryReorganize(coachesCount, requiredCoachesOrder);

                if (isReorganized) result.add(POSITIVE);
                else result.add(NEGATIVE);
            }
        }

        result.remove(result.size() - 1);
        result.forEach(System.out::println);
    }

    private static void validate(int coachCount, Integer[] coaches) {
        List<Integer> validateDigits = new ArrayList<>();
        for (Integer digit : coaches) {
            if (digit < 1 || digit > coachCount || validateDigits.contains(digit)) {
                String error = "Coach number is in an invalid range or number of the coach is repeated several times";
                throw new IllegalArgumentException(error);
            }
            validateDigits.add(digit);
        }
    }
}
