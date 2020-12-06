package be.devriendt.advent.s2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day6CustomCustoms {

    public static int getAnyoneYesAnswers(List<String> input) {
        int sum = 0;
        HashSet<Character> yesAnswers = new HashSet<>();

        for (String line: input) {
            if (line.isEmpty()) {
                sum += yesAnswers.size();
                yesAnswers.clear();
            } else {
                line.chars().forEach(c -> yesAnswers.add((char)c));
            }
        }

        sum += yesAnswers.size();
        return sum;
    }

    public static int getEveryoneYesAnswers(List<String> input) {
        int sum = 0;
        int groupMembers = 0;
        HashMap<Character, Integer> yesAnswers = new HashMap<>();

        for (String line: input) {
            if (line.isEmpty()) {
                sum += getAnswersWithNumberOfResponses(yesAnswers, groupMembers);
                yesAnswers.clear();
                groupMembers = 0;
            } else {
                line.chars().forEach(c -> yesAnswers.merge((char)c, 1, Integer::sum));
                groupMembers++;
            }
        }

        sum += getAnswersWithNumberOfResponses(yesAnswers, groupMembers);
        return sum;
    }

    private static int getAnswersWithNumberOfResponses(HashMap<Character, Integer> yesAnswers, final int numberOfResponses) {
        return (int) yesAnswers.values().stream()
                .filter(v -> v.equals(numberOfResponses))
                .count();
    }
}
