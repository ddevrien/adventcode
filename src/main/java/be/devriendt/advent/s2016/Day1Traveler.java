package be.devriendt.advent.s2016;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static be.devriendt.advent.s2016.Day1Traveler.FacingDirection.*;
import static be.devriendt.advent.s2016.Day1Traveler.WalkingDirection.LEFT;
import static be.devriendt.advent.s2016.Day1Traveler.WalkingDirection.RIGHT;

/**
 * Created by Dennis on 2/12/2016.
 */
public class Day1Traveler {

    enum FacingDirection {
        NORTH, EAST, SOUTH, WEST
    }

    enum WalkingDirection {
        LEFT, RIGHT
    }

    public static int getDistanceFromStartingLocation(String moveInstructions) {
        String[] instructions = moveInstructions.replace(" ", "").split(",");
        FacingDirection facingDirection = NORTH;
        Point location = new Point(0, 0);

        for (String instruction: instructions) {
            int distance = Integer.parseInt(instruction.substring(1, instruction.length()));
            WalkingDirection walkingDirection = instruction.charAt(0) == 'L' ? LEFT : RIGHT;
            facingDirection = getNewFacingDirection(facingDirection, walkingDirection);
            location = move(location, facingDirection, distance);
        }

        return 0;
    }

    private static Point move(Point location, FacingDirection facingDirection, int distance) {
        switch (facingDirection) {
            case NORTH: return new Point(location.x, location.y);
        }
        return null;
    }

    private static FacingDirection getNewFacingDirection(FacingDirection facingDirection, WalkingDirection walkingDirection) {
        switch (facingDirection) {
            case NORTH: return walkingDirection == LEFT ? WEST : EAST;
            case EAST: return walkingDirection == LEFT ? NORTH : SOUTH;
            case SOUTH: return walkingDirection == LEFT ? EAST : WEST;
            case WEST: return walkingDirection == LEFT ? SOUTH : NORTH;
            default:
                throw new IllegalArgumentException("what");
        }
    }

    public static int getEscapeToUnescapeCharacterDiff(String resourcePath) throws URISyntaxException, IOException {
        return escapeToUnescapeCharacterDiff(
                Files.readAllLines(Paths.get(Day1Traveler.class.getResource(resourcePath).toURI())));
    }

    public static int getUnescapeToEscapeCharacterDiff(String resourcePath) throws URISyntaxException, IOException {
        return unescapeToEscapeCharacterDiff(
                Files.readAllLines(Paths.get(Day1Traveler.class.getResource(resourcePath).toURI())));
    }

    public static int escapeToUnescapeCharacterDiff(List<String> strings) {
        int totalDiff = 0;
        for (String s: strings) {
            totalDiff += escapeToUnescapeCharacterDiff(s);
        }
        return totalDiff;
    }

    public static int unescapeToEscapeCharacterDiff(List<String> strings) {
        int totalDiff = 0;
        for (String s: strings) {
            totalDiff += unescapeToEscapeCharacterDiff(s);
        }
        return totalDiff;
    }

    public static int escapeToUnescapeCharacterDiff(String string) {
        int escapedLength = string.length();
        if (escapedLength == 2) {
            return 2;
        }

        int unescapedLength = 0;
        char[] chars = new char[string.length()-2];
        string.getChars(1, string.length()-1, chars, 0); // strip beginning and ending quote
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\') {
                i = chars[i + 1] == 'x' ? i + 3: i + 1;
            }
            unescapedLength++;
        }
        return escapedLength - unescapedLength;
    }

    public static int unescapeToEscapeCharacterDiff(String string) {
        int unescapedLength = string.length();
        if (unescapedLength == 2) {
            return 6 - 2;
        }

        int escapedLength = 6;
        char[] chars = new char[string.length()-2];
        string.getChars(1, string.length()-1, chars, 0); // strip beginning and ending quote
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\') {
                if (chars[i + 1] == 'x') {
                    i += 3;
                    escapedLength += 4;
                } else {
                    i++;
                    escapedLength += 3;
                }
            }
            escapedLength++;
        }
        return escapedLength - unescapedLength;
    }
}
