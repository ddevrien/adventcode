package be.devriendt.advent.s2020;

import java.util.List;

public class Day5BinaryBoarding {

    public static int toMissingSeatId(List<String> input) {
        Integer[] boardingPasses = input.stream()
                .map(boardingPass -> toSeatId(boardingPass))
                .sorted()
                .toArray(Integer[]::new);

        for(int i = 0; i < boardingPasses.length - 1; i++) {
            if (boardingPasses[i+1] - boardingPasses[i] > 1) {
                return boardingPasses[i] + 1;
            }
        }

        return -1;
    }

    public static int toSeatIdMax(List<String> input) {
        return input.stream()
                .mapToInt(boardingPass -> toSeatId(boardingPass))
                .max()
                .getAsInt();
    }

    public static int toSeatId(String input) {
        int rowLowerBound = 0;
        int rowUpperBound = 127;
        int columnLowerBound = 0;
        int columnUpperBound = 7;

        char[] chars = input.toCharArray();
        for (int i = 0; i < 7; i++) {
            if (chars[i] == 'F') {
                rowUpperBound = (rowLowerBound + rowUpperBound)/2;
            } else if (chars[i] == 'B') {
                rowLowerBound = (rowLowerBound + rowUpperBound)/2 + 1;
            } else {
                throw new IllegalArgumentException("unknown char: " + chars[i]);
            }
        }

        for (int i = 7; i < 10; i++) {
            if (chars[i] == 'L') {
                columnUpperBound = (columnLowerBound + columnUpperBound)/2;
            } else if (chars[i] == 'R') {
                columnLowerBound = (columnLowerBound + columnUpperBound)/2 + 1;
            } else {
                throw new IllegalArgumentException("unknown char: " + chars[i]);
            }
        }

        return rowLowerBound * 8 + columnLowerBound;
    }
}
