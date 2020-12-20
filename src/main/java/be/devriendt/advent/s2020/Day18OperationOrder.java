package be.devriendt.advent.s2020;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18OperationOrder {

    private static final Pattern PARENTHESES_PATTERN = Pattern.compile("\\([^\\(\\)]*\\)");
    private static final Pattern ADDITION_PATTERN = Pattern.compile("\\d+ \\+ \\d+");

    public static long calculateExpression(List<String> input) {
        return input.stream()
                .mapToLong(Day18OperationOrder::calculateExpression)
                .sum();
    }

    private static final long calculateExpression(String input) {
        return calculateExpression(input, false);
    }

    public static long calculateExpressionAdditionPrecedence(List<String> input) {
        return input.stream()
                .mapToLong(Day18OperationOrder::calculateExpressionAdditionPrecedence)
                .sum();
    }

    private static final long calculateExpressionAdditionPrecedence(String input) {
        return calculateExpression(input, true);
    }

    private static final long calculateExpression(String input, boolean additionPrecedence) {
        while (input.indexOf('(') != -1 || input.indexOf(')') != -1) {
            String[] groups = PARENTHESES_PATTERN
                    .matcher(input)
                    .results()
                    .map(MatchResult::group)
                    .toArray(String[]::new);

            for (String group: groups) {
                input = input.replace(group, String.valueOf(calculateExpression(group.substring(1, group.length()-1), additionPrecedence)));
            }
        }

        if (additionPrecedence) {
            boolean additionLeft = true;
            while (additionLeft) {
                Matcher m = ADDITION_PATTERN.matcher(input);
                if (m.find()) {
                    String group = m.group();
                    input = input.replace(group, String.valueOf(calculateExpression(group, false)));
                } else {
                    additionLeft = false;
                }
            }
        }

        String[] parts = input.split(" ");

        long value = Long.parseLong(parts[0]);
        for (int i = 1; i < parts.length; i += 2) {
            switch (parts[i]) {
                case "+":
                    value += Long.parseLong(parts[i+1]);
                    break;
                case "*":
                    value *= Long.parseLong(parts[i+1]);
            }
        }

        return value;
    }

}
