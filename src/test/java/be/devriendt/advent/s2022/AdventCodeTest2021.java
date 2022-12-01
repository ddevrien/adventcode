package be.devriendt.advent.s2022;

import be.devriendt.advent.s2021.Day1CalorieCounting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdventCodeTest2021 {

    @Test
    public void day1_calorieCounting() throws Exception {
        List<String> example = List.of("1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000".split("\n"));

        String input = "/s2021/day1_calories.txt";

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
}
