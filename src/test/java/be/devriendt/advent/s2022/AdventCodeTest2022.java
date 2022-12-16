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

    @Test
    public void day11_monkeyInTheMiddle() throws Exception {
        List<String> example = List.of(
                "Monkey 0:",
                "  Starting items: 79, 98",
                "  Operation: new = old * 19",
                "  Test: divisible by 23",
                "    If true: throw to monkey 2",
                "    If false: throw to monkey 3",
                "",
                "Monkey 1:",
                "  Starting items: 54, 65, 75, 74",
                "  Operation: new = old + 6",
                "  Test: divisible by 19",
                "    If true: throw to monkey 2",
                "    If false: throw to monkey 0",
                "",
                "Monkey 2:",
                "  Starting items: 79, 60, 97",
                "  Operation: new = old * old",
                "  Test: divisible by 13",
                "    If true: throw to monkey 1",
                "    If false: throw to monkey 3",
                "",
                "Monkey 3:",
                "  Starting items: 74",
                "  Operation: new = old + 3",
                "  Test: divisible by 17",
                "    If true: throw to monkey 0",
                "    If false: throw to monkey 1");

        String input = "/s2022/day11_monkeys.txt";

        Assertions.assertEquals(10605, Day11MonkeyInTheMiddle.findMonkeyBusiness(example, 20, 3));

        long time = System.currentTimeMillis();
        long solution = Day11MonkeyInTheMiddle.findMonkeyBusiness(Util.getContent(input), 20, 3);
        Assertions.assertEquals(57348, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 11: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(2713310158L, Day11MonkeyInTheMiddle.findMonkeyBusiness(example, 10_000, 1));

        time = System.currentTimeMillis();
        solution = Day11MonkeyInTheMiddle.findMonkeyBusiness(Util.getContent(input), 10_000, 1);
        Assertions.assertEquals(14106266886L, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 11 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day12_hillClimbingAlgorithm() throws Exception {
        List<String> example = List.of(
                "Sabqponm",
                "abcryxxl",
                "accszExk",
                "acctuvwj",
                "abdefghi");

        String input = "/s2022/day12_map.txt";

        Assertions.assertEquals(31, Day12HillClimbing.findNrOfStepsToDestination(example));

        long time = System.currentTimeMillis();
        int solution = Day12HillClimbing.findNrOfStepsToDestination(Util.getContent(input));
        Assertions.assertEquals(370, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 12: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(29, Day12HillClimbing.findOptimalStepsToDestination(example));

        time = System.currentTimeMillis();
        solution = Day12HillClimbing.findOptimalStepsToDestination(Util.getContent(input));
        Assertions.assertEquals(363, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 12 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day13_distressSignal() throws Exception {
        List<String> example = List.of(
                "[1,1,3,1,1]",
                "[1,1,5,1,1]",
                "",
                "[[1],[2,3,4]]",
                "[[1],4]",
                "",
                "[9]",
                "[[8,7,6]]",
                "",
                "[[4,4],4,4]",
                "[[4,4],4,4,4]",
                "",
                "[7,7,7,7]",
                "[7,7,7]",
                "",
                "[]",
                "[3]",
                "",
                "[[[]]]",
                "[[]]",
                "",
                "[1,[2,[3,[4,[5,6,7]]]],8,9]",
                "[1,[2,[3,[4,[5,6,0]]]],8,9]");

        String input = "/s2022/day13_packets.txt";

        Assertions.assertEquals(13, Day13DistressSignal.findSumOfPacketPairsIndicesInRightOrder(example));

        long time = System.currentTimeMillis();
        int solution = Day13DistressSignal.findSumOfPacketPairsIndicesInRightOrder(Util.getContent(input));
        Assertions.assertEquals(6478, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 13: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(140, Day13DistressSignal.sortPacketsInRightOrder(example));

        time = System.currentTimeMillis();
        solution = Day13DistressSignal.sortPacketsInRightOrder(Util.getContent(input));
        Assertions.assertEquals(21922, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 13 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day14_regolithReservoir() throws Exception {
        List<String> example = List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9");

        String input = "/s2022/day14_cave.txt";

        Assertions.assertEquals(24, Day14RegolithReservoir.findTotalSandUnits(example, true));

        long time = System.currentTimeMillis();
        int solution = Day14RegolithReservoir.findTotalSandUnits(Util.getContent(input), true);
        Assertions.assertEquals(873, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 14: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(93, Day14RegolithReservoir.findTotalSandUnits(example, false));

        time = System.currentTimeMillis();
        solution = Day14RegolithReservoir.findTotalSandUnits(Util.getContent(input), false);
        Assertions.assertEquals(24813, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 14 extra: " + solution + " [" + time + "ms]");
    }
}
