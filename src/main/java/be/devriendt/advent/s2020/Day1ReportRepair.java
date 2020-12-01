package be.devriendt.advent.s2020;

import be.devriendt.advent.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day1ReportRepair {

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
