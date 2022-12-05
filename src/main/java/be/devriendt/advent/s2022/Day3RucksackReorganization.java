package be.devriendt.advent.s2022;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Day3RucksackReorganization {

    public static int findPrioritySum(List<String> input) {
        return input.stream()
                .map(Day3RucksackReorganization::findDuplicateItemPriority)
                .reduce(0, Integer::sum);
    }

    private static int findDuplicateItemPriority(String line) {
        String left = line.substring(0, line.length() / 2);
        String right = line.substring(line.length() / 2);
        Set<Integer> items = left.chars().boxed().collect(toSet());
        return getPriorityOfDuplicate(items, right);
    }

    public static int findPrioritySum3(List<String> input) {
        int sum = 0;

        for (int i = 0; i < input.size(); i += 3) {
            Set<Integer> items = input.get(i).chars().boxed().collect(toSet());
            Set<Integer> doubles = input.get(i+1).chars().boxed().filter(items::contains).collect(toSet());
            sum += getPriorityOfDuplicate(doubles, input.get(i + 2));
        }

        return sum;
    }

    private static int getPriorityOfDuplicate(Set<Integer> items, String rucksack) {
        for (char item: rucksack.toCharArray()) {
            if (items.contains((int) item)) {
                return item < 'a' ? item - 'A' + 1 + 26 : item - 'a' + 1;
            }
        }

        throw new IllegalArgumentException("No duplicate found");
    }
}
