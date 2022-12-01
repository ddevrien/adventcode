package be.devriendt.advent.s2021;

import be.devriendt.advent.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day1CalorieCounting {

    public static int findElfCarryingMost(String filename) throws IOException, URISyntaxException {
        List<String> calories = Util.getContent(filename);
        return findElfCarryingMost(calories);
    }

    public static int findElfCarryingMost(List<String> calories) {
        int mostCalories = 0;
        int currentSum = 0;

        for(String entry: calories) {
            if(entry.isEmpty()) {
                mostCalories = Math.max(mostCalories, currentSum);
                currentSum = 0;
            } else {
                currentSum += Integer.parseInt(entry);
            }
        }
        mostCalories = Math.max(mostCalories, currentSum);

        return mostCalories;
    }

    public static int findTopElvesCarryingMost(String filename, int top) throws IOException, URISyntaxException {
        List<String> calories = Util.getContent(filename);
        return findTopElvesCarryingMost(calories, top);
    }

    public static int findTopElvesCarryingMost(List<String> calories, int top) {
        TreeSet<Integer> elvesCalories = new TreeSet<>();
        int currentSum = 0;

        for(String entry: calories) {
            if(entry.isEmpty()) {
                elvesCalories.add(currentSum);
                currentSum = 0;
            } else {
                currentSum += Integer.parseInt(entry);
            }
        }
        elvesCalories.add(currentSum);

        Iterator<Integer> iterator = elvesCalories.descendingIterator();
        int totalSum = 0;
        for (int i = 0; i < top; i++) {
            totalSum += iterator.next();
        }
        return totalSum;
    }

}
