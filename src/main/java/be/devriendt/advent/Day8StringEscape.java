package be.devriendt.advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Dennis on 24/01/2016.
 */
public class Day8StringEscape {

    public static int getEscapeToUnescapeCharacterDiff(String resourcePath) throws URISyntaxException, IOException {
        return escapeToUnescapeCharacterDiff(
                Files.readAllLines(Paths.get(Day8StringEscape.class.getResource(resourcePath).toURI())));
    }

    public static int getUnescapeToEscapeCharacterDiff(String resourcePath) throws URISyntaxException, IOException {
        return unescapeToEscapeCharacterDiff(
                Files.readAllLines(Paths.get(Day8StringEscape.class.getResource(resourcePath).toURI())));
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
