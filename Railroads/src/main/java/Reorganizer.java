import java.util.Deque;
import java.util.Stack;

/**
 * Coaches reorganizer class.
 */
public class Reorganizer {

    /**
     * Returns true if possible reorganize coaches in order presented by the queue coaches.
     */
    public static boolean tryReorganize(int coachesCount, Deque<Integer> coaches) {
        if (coaches.isEmpty()) return true;

        Stack<Integer> station = new Stack<>();
        int currentCoachNumber = 1;

        while (true) {
            Integer necessaryCoachNumber = coaches.peek();
            if (!necessaryCoachNumber.equals(currentCoachNumber)) {
                if (!station.isEmpty() && station.peek().equals(necessaryCoachNumber)) {
                    station.pop();
                    coaches.pop();
                }
                if (currentCoachNumber <= coachesCount) station.push(currentCoachNumber);
            }
            else coaches.pop();
            currentCoachNumber++;

            if (coaches.isEmpty()) return true;

            if (!station.isEmpty() && currentCoachNumber > coachesCount && !station.peek().equals(coaches.peek())) {
                return false;
            }
        }
    }
}
