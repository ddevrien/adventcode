package be.devriendt.advent.s2020;

import be.devriendt.advent.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class AdventCodeTest2020 {

    @Test
    public void day1_reportRepair() throws Exception {
        Integer[] example = {1721, 979, 366, 299, 675, 1456};
        String input = "/s2020/day1_expensereport.txt";

        Assertions.assertEquals(514579, Day1ReportRepair.expensesToTwoEntrySolution(example));

        long time = System.currentTimeMillis();
        int solution = Day1ReportRepair.expensesToTwoEntrySolution(input);
        Assertions.assertEquals(974304, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(241861950, Day1ReportRepair.expensesToThreeEntrySolution(example));

        time = System.currentTimeMillis();
        solution = Day1ReportRepair.expensesToThreeEntrySolution(input);
        Assertions.assertEquals(236430480, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day2_passwordPhilosophy() throws Exception {
        List<String> example = asList("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc");
        List<String> input = Util.getContent("/s2020/day2_passwords.txt");

        Assertions.assertEquals(2, Day2PasswordPhilosophy.numberOfValidPasswords(example));

        long time = System.currentTimeMillis();
        int solution = Day2PasswordPhilosophy.numberOfValidPasswords(input);
        Assertions.assertEquals(465, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 2: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(1, Day2PasswordPhilosophy.numberOfValidPasswords2(example));

        time = System.currentTimeMillis();
        solution = Day2PasswordPhilosophy.numberOfValidPasswords2(input);
        Assertions.assertEquals(294, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 2 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day3_tobogganTrajectory() throws Exception {
        String[] example = "..##.......\n#...#...#..\n.#....#..#.\n..#.#...#.#\n.#...##..#.\n..#.##.....\n.#.#.#....#\n.#........#\n#.##...#...\n#...##....#\n.#..#...#.#".split("\n");
        String[] input = Util.getContent("/s2020/day3_map.txt").toArray(String[]::new);
        Day3TobogganTrajectory.Instruction instruction = new Day3TobogganTrajectory.Instruction(3, 1);
        List<Day3TobogganTrajectory.Instruction> instructions = asList(
                new Day3TobogganTrajectory.Instruction(1, 1),
                new Day3TobogganTrajectory.Instruction(3, 1),
                new Day3TobogganTrajectory.Instruction(5, 1),
                new Day3TobogganTrajectory.Instruction(7, 1),
                new Day3TobogganTrajectory.Instruction(1, 2));

        Assertions.assertEquals(7, Day3TobogganTrajectory.countNumberOfTrees(example, instruction));

        long time = System.currentTimeMillis();
        int solution = Day3TobogganTrajectory.countNumberOfTrees(input, instruction);
        Assertions.assertEquals(181, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 3: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(336, Day3TobogganTrajectory.countNumberOfTrees(example, instructions));

        time = System.currentTimeMillis();
        solution = Day3TobogganTrajectory.countNumberOfTrees(input, instructions);
        Assertions.assertEquals(1260601650, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 3 extra: " + solution + " [" + time + "ms]");
    }
}