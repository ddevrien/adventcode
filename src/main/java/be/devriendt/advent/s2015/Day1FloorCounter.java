package be.devriendt.advent.s2015;

/**
 * Created by Dennis on 5/12/2015.
 */
public class Day1FloorCounter {

    private static final char UP = '(';

    private static final char DOWN = ')';

    public static int directionToFloor(String input) {
        int floor = 0;
        for (char c: input.toCharArray()) {
            if (c == UP) {
                floor++;
            } else if (c == DOWN) {
                floor--;
            } else {
                throw new IllegalArgumentException("Unexpected symbol: " + c);
            }
        }
        return floor;
    }

    public static int findBasementEnterPosition(String input) {
        int floor = 0;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == UP) {
                floor++;
            } else if (c == DOWN) {
                floor--;
                if (floor == -1) {
                    return ++i;
                }
            } else {
                throw new IllegalArgumentException("Unexpected symbol: " + c);
            }
        }
        return floor;
    }
}
