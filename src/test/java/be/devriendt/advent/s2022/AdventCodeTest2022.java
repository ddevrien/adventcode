package be.devriendt.advent.s2022;

import be.devriendt.advent.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdventCodeTest2022 {

    @Test
    public void day1_calorieCounting() throws Exception {
        List<String> example = List.of("1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000".split("\n"));

        String input = "/s2022/day1_calories.txt";

        Assertions.assertEquals(24000, Day1CalorieCounting.findElfCarryingMost(example));

        long time = System.currentTimeMillis();
        int solution = Day1CalorieCounting.findElfCarryingMost(input);
        Assertions.assertEquals(75501, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(45000, Day1CalorieCounting.findTopElvesCarryingMost(example, 3));

        time = System.currentTimeMillis();
        solution = Day1CalorieCounting.findTopElvesCarryingMost(input, 3);
        Assertions.assertEquals(215594, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day2_rockPaperScissors() throws Exception {
        List<String> example = List.of("A Y", "B X", "C Z");

        String input = "/s2022/day2_rsp.txt";

        Assertions.assertEquals(15, Day2RockPaperScissors.executeWithStrategy(example));

        long time = System.currentTimeMillis();
        int solution = Day2RockPaperScissors.executeWithStrategy(Util.getContent(input));
        Assertions.assertEquals(9759, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 2: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(12, Day2RockPaperScissors.executeWithStrategy2(example));

        time = System.currentTimeMillis();
        solution = Day2RockPaperScissors.executeWithStrategy2(Util.getContent(input));
        Assertions.assertEquals(12429, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 2 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day3_rucksackReorganization() throws Exception {
        List<String> example = List.of("vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw");

        String input = "/s2022/day3_rucksacks.txt";

        Assertions.assertEquals(157, Day3RucksackReorganization.findPrioritySum(example));

        long time = System.currentTimeMillis();
        int solution = Day3RucksackReorganization.findPrioritySum(Util.getContent(input));
        Assertions.assertEquals(8401, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 3: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(70, Day3RucksackReorganization.findPrioritySum3(example));

        time = System.currentTimeMillis();
        solution = Day3RucksackReorganization.findPrioritySum3(Util.getContent(input));
        Assertions.assertEquals(2641, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 3 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day4_campCleanup() throws Exception {
        List<String> example = List.of("2-4,6-8",
                "2-3,4-5",
                "5-7,7-9",
                "2-8,3-7",
                "6-6,4-6",
                "2-6,4-8");

        String input = "/s2022/day4_cleanup.txt";

        Assertions.assertEquals(2, Day4CampCleanup.findNumberOfFullyContainedSections(example));

        long time = System.currentTimeMillis();
        int solution = Day4CampCleanup.findNumberOfFullyContainedSections(Util.getContent(input));
        Assertions.assertEquals(513, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 4: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(4, Day4CampCleanup.findNumberOfPartiallyContainedSections(example));

        time = System.currentTimeMillis();
        solution = Day4CampCleanup.findNumberOfPartiallyContainedSections(Util.getContent(input));
        Assertions.assertEquals(878, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 4 extra: " + solution + " [" + time + "ms]");
    }
}
