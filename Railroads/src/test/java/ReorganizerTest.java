import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ReorganizerTest {
    private final Deque<Integer> necessaryOrder = new ArrayDeque<>();

    private Deque<Integer> getDeque(String digits) {
        necessaryOrder.clear();
        Integer[] d = StringParser.parseDigits(digits);
        necessaryOrder.addAll(Arrays.asList(d));
        return necessaryOrder;
    }

    @Test
    public void oneCoach() {
        Deque<Integer> test = getDeque("1");
        Assert.assertTrue(Reorganizer.tryReorganize(1, test));
    }

    @Test
    public void twoCoaches() {
        Deque<Integer> test = getDeque("1 2");
        Assert.assertTrue(Reorganizer.tryReorganize(2, test));

        Deque<Integer> test2 = getDeque("2 1");
        Assert.assertTrue(Reorganizer.tryReorganize(2, test2));
    }

    @Test
    public void threeCoaches() {
        Deque<Integer> test = getDeque("1 2 3");
        Assert.assertTrue(Reorganizer.tryReorganize(3, test));

        Deque<Integer> test2 = getDeque("1 3 2");
        Assert.assertTrue(Reorganizer.tryReorganize(3, test2));

        Deque<Integer> test3 = getDeque("2 1 3");
        Assert.assertTrue(Reorganizer.tryReorganize(3, test3));

        Deque<Integer> test4 = getDeque("2 3 1");
        Assert.assertTrue(Reorganizer.tryReorganize(3, test4));

        Deque<Integer> test5 = getDeque("3 1 2");
        Assert.assertFalse(Reorganizer.tryReorganize(3, test5));

        Deque<Integer> test6 = getDeque("3 2 1");
        Assert.assertTrue(Reorganizer.tryReorganize(3, test6));
    }

    @Test
    public void maxCoachesCount() {
        int N = 1000;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            builder.append(i);
            if (i < N) builder.append(" ");
        }

        Deque<Integer> test = getDeque(builder.toString());
        Assert.assertTrue(Reorganizer.tryReorganize(N, test));

        StringBuilder builder1 = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            builder1.append(N - i + 1);
            if (i < N) builder1.append(" ");
        }

        Deque<Integer> test1 = getDeque(builder1.toString());
        Assert.assertTrue(Reorganizer.tryReorganize(N, test1));
    }

    @Test
    public void impossibleReorganized() {
        String line = "5 2 3 1 4";
        Deque<Integer> test = getDeque(line);
        Assert.assertFalse(Reorganizer.tryReorganize(5, test));

        line = "5 4 1 2 3";
        test = getDeque(line);
        Assert.assertFalse(Reorganizer.tryReorganize(5, test));

    }

}
