package be.devriendt.advent.s2022;

public class Day6TuningTrouble {

    private static final int WINDOW_SIZE_SMALL = 4;
    private static final int WINDOW_SIZE_LARGE = 14;

    public static int findMarker(String input) {
        return findMarker(input, WINDOW_SIZE_SMALL);
    }

    public static int findMarker2(String input) {
        return findMarker(input, WINDOW_SIZE_LARGE);
    }

    private static int findMarker(String input, int windowSize) {
        for (int i = 0; i < input.length() - windowSize; i++) {
            String window = input.substring(i, i + windowSize);
            if (allUniqueChars(window)) {
                return i + windowSize;
            }
        }
        
        throw new IllegalArgumentException("No solution");
    }

    private static boolean allUniqueChars(String window) {
        for (int i = 0; i < window.length(); i++) {
            char cursor = window.charAt(i);
            for (int j = i + 1; j < window.length(); j++) {
                if (cursor == window.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
