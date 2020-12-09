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
}