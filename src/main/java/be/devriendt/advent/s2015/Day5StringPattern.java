package be.devriendt.advent.s2015;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dennis on 5/12/2015.
 */
public class Day5StringPattern {

    private static final String VOWELS = "aeiou";

    private static final List<String> NAUGHTY_STRINGS = Arrays.asList("ab", "cd", "pq", "xy");

    public static int countNumberOfNiceStrings(String resourcePath) throws URISyntaxException, IOException {
        return (int)Files.lines(Paths.get(Day5StringPattern.class.getResource(resourcePath).toURI()))
                .filter(l -> Day5StringPattern.isNiceString(l))
                .count();
    }

    public static int countNumberOfSuperNiceStrings(String resourcePath) throws URISyntaxException, IOException {
        return (int)Files.lines(Paths.get(Day5StringPattern.class.getResource(resourcePath).toURI()))
                .filter(l -> Day5StringPattern.isSuperNiceString(l))
                .count();
    }

    public static boolean isNiceString(String input) {
        int vowels = 0;
        boolean containsRepeatLetter = false;
        boolean containsNaughtyLetter = false;

        char previousChar = 0;

        for (char c: input.toCharArray()) {
            if (vowels < 3 && VOWELS.indexOf(c) != -1) {
                vowels++;
            }
            if (!containsRepeatLetter && previousChar == c) {
                containsRepeatLetter = true;
            }
            if (!containsNaughtyLetter && isNaughtyString(new String(new char[]{previousChar, c}))) {
                containsNaughtyLetter = true;
            }

            previousChar = c;
        }

        return vowels == 3 && containsRepeatLetter && !containsNaughtyLetter;
    }

    public static boolean isSuperNiceString(String input) {
        return input.matches(".*(..).*\\1.*") && input.matches(".*(.).\\1.*");
    }

    public static boolean isNaughtyString(String input) {
        return NAUGHTY_STRINGS.contains(input);
    }
}
