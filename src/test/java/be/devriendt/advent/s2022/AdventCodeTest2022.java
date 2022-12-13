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

    @Test
    public void day5_supplyStacks() throws Exception {
        List<String> example = List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 ",
                "",
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2");

        String input = "/s2022/day5_stacks.txt";

        Assertions.assertEquals("CMZ", Day5SupplyStacks.findTopOfStackCrates(example));

        long time = System.currentTimeMillis();
        String solution = Day5SupplyStacks.findTopOfStackCrates(Util.getContent(input));
        Assertions.assertEquals("BZLVHBWQF", solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 5: " + solution + " [" + time + "ms]");

        Assertions.assertEquals("MCD", Day5SupplyStacks.findTopOfStackCrates(example, true));

        time = System.currentTimeMillis();
        solution = Day5SupplyStacks.findTopOfStackCrates(Util.getContent(input), true);
        Assertions.assertEquals("TDGJQTZSL", solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 5 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day6_tuningTrouble() throws Exception {
        String example = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        String example2 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        String example3 = "nppdvjthqldpwncqszvftbrmjlhg";
        String example4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        String example5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

        String input = "/s2022/day6_datastream.txt";

        Assertions.assertEquals(7, Day6TuningTrouble.findMarker(example));
        Assertions.assertEquals(5, Day6TuningTrouble.findMarker(example2));
        Assertions.assertEquals(6, Day6TuningTrouble.findMarker(example3));
        Assertions.assertEquals(10, Day6TuningTrouble.findMarker(example4));
        Assertions.assertEquals(11, Day6TuningTrouble.findMarker(example5));

        long time = System.currentTimeMillis();
        int solution = Day6TuningTrouble.findMarker(Util.getContentSingleLine(input));
        Assertions.assertEquals(1707, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 6: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(19, Day6TuningTrouble.findMarker2(example));
        Assertions.assertEquals(23, Day6TuningTrouble.findMarker2(example2));
        Assertions.assertEquals(23, Day6TuningTrouble.findMarker2(example3));
        Assertions.assertEquals(29, Day6TuningTrouble.findMarker2(example4));
        Assertions.assertEquals(26, Day6TuningTrouble.findMarker2(example5));

        time = System.currentTimeMillis();
        solution = Day6TuningTrouble.findMarker2(Util.getContentSingleLine(input));
        Assertions.assertEquals(3697, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 6 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day7_noSpaceLeftOnDevice() throws Exception {
        List<String> example = List.of("$ cd /",
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat",
                "dir d",
                "$ cd a",
                "$ ls",
                "dir e",
                "29116 f",
                "2557 g",
                "62596 h.lst",
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd ..",
                "$ cd ..",
                "$ cd d",
                "$ ls",
                "4060174 j",
                "8033020 d.log",
                "5626152 d.ext",
                "7214296 k");

        String input = "/s2022/day7_terminal.txt";

        Assertions.assertEquals(95437, Day7NoSpaceLeftOnDevice.findTotalSizeOfDirectoriesToDelete(example));

        long time = System.currentTimeMillis();
        int solution = Day7NoSpaceLeftOnDevice.findTotalSizeOfDirectoriesToDelete(Util.getContent(input));
        Assertions.assertEquals(1454188, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 7: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(24933642, Day7NoSpaceLeftOnDevice.findDirectoryToDelete(example));

        time = System.currentTimeMillis();
        solution = Day7NoSpaceLeftOnDevice.findDirectoryToDelete(Util.getContent(input));
        Assertions.assertEquals(4183246, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 7 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day8_treetopTreeHouse() throws Exception {
        List<String> example = List.of(
                "30373",
                "25512",
                "65332",
                "33549",
                "35390");

        String input = "/s2022/day8_forest.txt";

        Assertions.assertEquals(21, Day8TreetopTreeHouse.getNumberOfVisibleTrees(example));

        long time = System.currentTimeMillis();
        int solution = Day8TreetopTreeHouse.getNumberOfVisibleTrees(Util.getContent(input));
        Assertions.assertEquals(1823, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 8: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(8, Day8TreetopTreeHouse.findBestTree(example));

        time = System.currentTimeMillis();
        solution = Day8TreetopTreeHouse.findBestTree(Util.getContent(input));
        Assertions.assertEquals(211680, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 8 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day9_ropeBridge() throws Exception {
        List<String> example = List.of(
                "R 4",
                "U 4",
                "L 3",
                "D 1",
                "R 4",
                "D 1",
                "L 5",
                "R 2");

        String input = "/s2022/day9_moves.txt";

        Assertions.assertEquals(13, Day9RopeBridge.findNumberOfVisitedLocations(2, example));

        long time = System.currentTimeMillis();
        int solution = Day9RopeBridge.findNumberOfVisitedLocations(2, Util.getContent(input));
        Assertions.assertEquals(6209, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 9: " + solution + " [" + time + "ms]");

        List<String> example2 = List.of(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20");
        Assertions.assertEquals(1, Day9RopeBridge.findNumberOfVisitedLocations(10, example));
        Assertions.assertEquals(36, Day9RopeBridge.findNumberOfVisitedLocations(10, example2));

        time = System.currentTimeMillis();
        solution = Day9RopeBridge.findNumberOfVisitedLocations(10, Util.getContent(input));
        Assertions.assertEquals(2460, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 9 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day10_cathodeRayTube() throws Exception {
        List<String> example = List.of(
                "noop",
                "addx 3",
                "addx -5");
        String example2 = "/s2022/day10_instructions_example.txt";

        String input = "/s2022/day10_instructions.txt";

        Assertions.assertEquals(0, Day10CRT.findSignalStrengthSum(example));
        Assertions.assertEquals(13140, Day10CRT.findSignalStrengthSum(Util.getContent(example2)));

        long time = System.currentTimeMillis();
        int solution = Day10CRT.findSignalStrengthSum(Util.getContent(input));
        Assertions.assertEquals(15260, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1O: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(
                "##..##..##..##..##..##..##..##..##..##..\n" +
                        "###...###...###...###...###...###...###.\n" +
                        "####....####....####....####....####....\n" +
                        "#####.....#####.....#####.....#####.....\n" +
                        "######......######......######......####\n" +
                        "#######.......#######.......#######.....", Day10CRT.renderScreen(Util.getContent(example2)));

        time = System.currentTimeMillis();
        Assertions.assertEquals(
                "###...##..#..#.####..##..#....#..#..##..\n" +
                        "#..#.#..#.#..#.#....#..#.#....#..#.#..#.\n" +
                        "#..#.#....####.###..#....#....#..#.#....\n" +
                        "###..#.##.#..#.#....#.##.#....#..#.#.##.\n" +
                        "#....#..#.#..#.#....#..#.#....#..#.#..#.\n" +
                        "#.....###.#..#.#.....###.####..##...###.", Day10CRT.renderScreen(Util.getContent(input)));
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 10 extra: " + solution + " [" + time + "ms]");
    }
}
