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

    @Test
    public void day4_passportProcessing() throws Exception {
        List<String> example = asList(("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929\n" +
                "\n" +
                "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm\n" +
                "\n" +
                "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in").split("\n"));
        List<String> input = Util.getContent("/s2020/day4_passports.txt");

        Assertions.assertEquals(2, Day4PassportProcessing.countNumberOfValidPassports(example));

        long time = System.currentTimeMillis();
        int solution = Day4PassportProcessing.countNumberOfValidPassports(input);
        Assertions.assertEquals(192, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 4: " + solution + " [" + time + "ms]");

        List<String> exampleInvalid = asList(("eyr:1972 cid:100\n" +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
                "\n" +
                "iyr:2019\n" +
                "hcl:#602927 eyr:1967 hgt:170cm\n" +
                "ecl:grn pid:012533040 byr:1946\n" +
                "\n" +
                "hcl:dab227 iyr:2012\n" +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" +
                "\n" +
                "hgt:59cm ecl:zzz\n" +
                "eyr:2038 hcl:74454a iyr:2023\n" +
                "pid:3556412378 byr:2007").split("\n"));
        List<String> exampleValid = asList(("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" +
                "\n" +
                "hcl:#888785\n" +
                "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                "pid:545766238 ecl:hzl\n" +
                "eyr:2022\n" +
                "\n" +
                "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719").split("\n"));

        Assertions.assertEquals(0, Day4PassportProcessing.countNumberOfValidPassportsWithDataValidation(exampleInvalid));
        Assertions.assertEquals(4, Day4PassportProcessing.countNumberOfValidPassportsWithDataValidation(exampleValid));

        time = System.currentTimeMillis();
        solution = Day4PassportProcessing.countNumberOfValidPassportsWithDataValidation(input);
        Assertions.assertEquals(101, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 4 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day5_binaryBoarding() throws Exception {
        String[] examples = {"FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"};
        List<String> input = Util.getContent("/s2020/day5_boardingpasses.txt");

        Assertions.assertEquals(357, Day5BinaryBoarding.toSeatId(examples[0]));
        Assertions.assertEquals(567, Day5BinaryBoarding.toSeatId(examples[1]));
        Assertions.assertEquals(119, Day5BinaryBoarding.toSeatId(examples[2]));
        Assertions.assertEquals(820, Day5BinaryBoarding.toSeatId(examples[3]));

        long time = System.currentTimeMillis();
        int solution = Day5BinaryBoarding.toSeatIdMax(input);
        Assertions.assertEquals(965, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 5: " + solution + " [" + time + "ms]");

        time = System.currentTimeMillis();
        solution = Day5BinaryBoarding.toMissingSeatId(input);
        Assertions.assertEquals(524, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 5 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day6_customCustoms() throws Exception {
        List<String> example = asList(("abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b").split("\n"));
        List<String> input = Util.getContent("/s2020/day6_answers.txt");

        Assertions.assertEquals(11, Day6CustomCustoms.getAnyoneYesAnswers(example));

        long time = System.currentTimeMillis();
        int solution = Day6CustomCustoms.getAnyoneYesAnswers(input);
        Assertions.assertEquals(6565, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 6: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(6, Day6CustomCustoms.getEveryoneYesAnswers(example));

        time = System.currentTimeMillis();
        solution = Day6CustomCustoms.getEveryoneYesAnswers(input);
        Assertions.assertEquals(3137, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 6 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day7_handyHaversacks() throws Exception {
        List<String> example = asList(("light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags.").split("\n"));
        List<String> input = Util.getContent("/s2020/day7_rules.txt");

        Assertions.assertEquals(4, Day7HandyHaversacks.getTotalValidCombinations("shiny gold", example));

        long time = System.currentTimeMillis();
        int solution = Day7HandyHaversacks.getTotalValidCombinations("shiny gold", input);
        Assertions.assertEquals(164, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 7: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(32, Day7HandyHaversacks.getTotalCost("shiny gold", example));

        time = System.currentTimeMillis();
        solution = Day7HandyHaversacks.getTotalCost("shiny gold", input);
        Assertions.assertEquals(7872, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 7 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day8_handheldHandling() throws Exception {
        List<String> example = asList(("nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6").split("\n"));
        List<String> input = Util.getContent("/s2020/day8_instructions.txt");

        Day8HandheldHandling.InfiniteLoopException e =
                Assertions.assertThrows(Day8HandheldHandling.InfiniteLoopException.class, () -> Day8HandheldHandling.getAccumulatorValue(example));
        Assertions.assertEquals(5, e.getAccumulator());

        long time = System.currentTimeMillis();
        e = Assertions.assertThrows(Day8HandheldHandling.InfiniteLoopException.class, () -> Day8HandheldHandling.getAccumulatorValue(input));
        Assertions.assertEquals(2034, e.getAccumulator());
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 8: " + e.getAccumulator() + " [" + time + "ms]");

        Assertions.assertEquals(8, Day8HandheldHandling.findInfiniteLoopFixAndGetAccumulatorValue(example));

        time = System.currentTimeMillis();
        int solution = Day8HandheldHandling.findInfiniteLoopFixAndGetAccumulatorValue(input);
        Assertions.assertEquals(672, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 8 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day9_encodingError() throws Exception {
        List<Long> example = asList(35L,20L,15L,25L,47L,40L,62L,55L,65L,95L,102L,117L,150L,182L,127L,219L,299L,277L,309L,576L);
        List<Long> input = Util.getContentAsLongs("/s2020/day9_numbers.txt");

        Assertions.assertEquals(127, Day9EncodingError.findInvalidNumber(5, example));

        long time = System.currentTimeMillis();
        long solution = Day9EncodingError.findInvalidNumber(25, input);
        Assertions.assertEquals(20874512, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 9: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(62, Day9EncodingError.findContiguousSetForInvalidNumber(5, example));

        time = System.currentTimeMillis();
        solution = Day9EncodingError.findContiguousSetForInvalidNumber(25, input);
        Assertions.assertEquals(3012420, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 9 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day10_adapterArray() throws Exception {
        List<Integer> example = asList(16,10,15,5,1,11,7,19,6,12,4);
        List<Integer> example2 = asList(28,33,18,42,31,14,46,20,48,47,24,23,49,45,19,38,39,11,1,32,25,35,8,17,7,9,4,2,34,10,3);
        List<Integer> input = Util.getContentAsIntegers("/s2020/day10_adapters.txt");

        Assertions.assertEquals(7*5, Day10AdapterArray.getJoltageDifference(example).diffs);
        Assertions.assertEquals(22*10, Day10AdapterArray.getJoltageDifference(example2).diffs);

        long time = System.currentTimeMillis();
        long solution = Day10AdapterArray.getJoltageDifference(input).diffs;
        Assertions.assertEquals(2080, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 10: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(8, Day10AdapterArray.getJoltagePermutations(example));
        Assertions.assertEquals(19208, Day10AdapterArray.getJoltagePermutations(example2));

        time = System.currentTimeMillis();
        solution = Day10AdapterArray.getJoltagePermutations(input);
        Assertions.assertEquals(6908379398144L, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 10 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day11_seatingSystem() throws Exception {
        List<String> example = asList((
                "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL").split("\n"));
        List<String> input = Util.getContent("/s2020/day11_floorplan.txt");

        Assertions.assertEquals(37, Day11SeatingSystem.getNrOfOccupiedSeats(example));

        long time = System.currentTimeMillis();
        int solution = Day11SeatingSystem.getNrOfOccupiedSeats(input);
        Assertions.assertEquals(2164, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 11: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(26, Day11SeatingSystem.getNrOfOccupiedSeatsPart2(example));

        time = System.currentTimeMillis();
        solution = Day11SeatingSystem.getNrOfOccupiedSeatsPart2(input);
        Assertions.assertEquals(1974, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 11 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day12_rainRisk() throws Exception {
        List<String> example = asList(("F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11").split("\n"));
        List<String> input = Util.getContent("/s2020/day12_instructions.txt");

        Assertions.assertEquals(25, Day12RainRisk.getManhattanDistance(example));

        long time = System.currentTimeMillis();
        int solution = Day12RainRisk.getManhattanDistance(input);
        Assertions.assertEquals(2297, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 12: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(286, Day12RainRisk.getManhattanDistanceWithWaypoint(example));

        time = System.currentTimeMillis();
        solution = Day12RainRisk.getManhattanDistanceWithWaypoint(input);
        Assertions.assertEquals(89984, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 12 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day13_shuttleSearch() throws Exception {
        List<String> example = asList("939", "7,13,x,x,59,x,31,19");
        List<String> example2 = asList("", "17,x,13,19");
        List<String> example3 = asList("", "67,7,59,61");
        List<String> example4 = asList("", "67,x,7,59,61");
        List<String> example5 = asList("", "67,7,x,59,61");
        List<String> example6 = asList("", "1789,37,47,1889");

        List<String> input = asList("1008832", "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,449,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,13,19,x,x,x,x,x,x,x,x,x,29,x,991,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,17");

        Assertions.assertEquals(295, Day13ShuttleSearch.getBusSolution(example));

        long time = System.currentTimeMillis();
        long solution = Day13ShuttleSearch.getBusSolution(input);
        Assertions.assertEquals(5946, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 13: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(1068781, Day13ShuttleSearch.getMatchingDepartureForBusses(example));
        Assertions.assertEquals(3417, Day13ShuttleSearch.getMatchingDepartureForBusses(example2));
        Assertions.assertEquals(754018, Day13ShuttleSearch.getMatchingDepartureForBusses(example3));
        Assertions.assertEquals(779210, Day13ShuttleSearch.getMatchingDepartureForBusses(example4));
        Assertions.assertEquals(1261476, Day13ShuttleSearch.getMatchingDepartureForBusses(example5));
        Assertions.assertEquals(1202161486, Day13ShuttleSearch.getMatchingDepartureForBusses(example6));

        time = System.currentTimeMillis();
//        solution = Day13ShuttleSearch.getMatchingDepartureForBusses(input); takes HOURS :D
//        Assertions.assertEquals(645338524823718L, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 13 extra: " + solution + " [" + time + "ms]");
    }

    @Test
    public void day14_dockingData() throws Exception {
        List<String> example = asList(("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0").split("\n"));
        List<String> example2 = asList(("mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1").split("\n"));
        List<String> input = Util.getContent("/s2020/day14_initprogram.txt");

        Assertions.assertEquals(165, Day14DockingData.getSumOfMemoryValues(example));

        long time = System.currentTimeMillis();
        long solution = Day14DockingData.getSumOfMemoryValues(input);
        Assertions.assertEquals(5875750429995L, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 14: " + solution + " [" + time + "ms]");

        Assertions.assertEquals(208, Day14DockingData.getSumOfMemoryValuesPart2(example2));

        time = System.currentTimeMillis();
        solution = Day14DockingData.getSumOfMemoryValuesPart2(input);
        Assertions.assertEquals(5272149590143L, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 12 extra: " + solution + " [" + time + "ms]");
    }
}