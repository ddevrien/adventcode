package be.devriendt.advent.s2020;

import be.devriendt.advent.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Day1ReportRepair {

    private static class Pair {
        final int a;
        final int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static int expensesToTwoEntrySolution(String filename) throws IOException, URISyntaxException {
        Integer[] numbers = getEntries(filename);
        return expensesToTwoEntrySolution(numbers);
    }

    public static int expensesToTwoEntrySolution(Integer... expenses) {
        for (int i = 0; i < expenses.length; i++) {
            for (int j = i+1; j < expenses.length; j++) {
                if (expenses[i] + expenses[j] == 2020) {
                    return expenses[i] * expenses[j];
                }
            }
        }

        return -1;
    }

    public static int expensesToTwoEntryStreamSolution(Integer... expenses) {
        Optional<Integer> solution = Arrays.stream(expenses)
                .flatMap(a -> Arrays.stream(expenses).map (b -> new Pair(a,b)))
                .filter(pair -> pair.a + pair.b == 2020)
                .map(pair -> pair.a * pair.b)
                .findFirst();

        return solution.orElse(-1);
    }

    public static int expensesToThreeEntrySolution(String filename) throws IOException, URISyntaxException {
        Integer[] numbers = getEntries(filename);
        return expensesToThreeEntrySolution(numbers);
    }

    public static int expensesToThreeEntrySolution(Integer... expenses) {
        for (int i = 0; i < expenses.length; i++) {
            for (int j = i+1; j < expenses.length; j++) {
                for (int k = j+1; k < expenses.length; k++) {
                    if (expenses[i] + expenses[j] + expenses[k] == 2020) {
                        return expenses[i] * expenses[j] * expenses[k];
                    }
                }
            }
        }

        return -1;
    }

    private static Integer[] getEntries(String filename) throws URISyntaxException, IOException {
        List<String> lines = Util.getContent(filename);
        Integer[] numbers = lines.stream()
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        return numbers;
    }
}
