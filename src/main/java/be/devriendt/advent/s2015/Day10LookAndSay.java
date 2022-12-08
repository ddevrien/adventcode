package be.devriendt.advent.s2015;

public class Day10LookAndSay {

    public static String execute(String input, int iterations) {
        StringBuilder sb = new StringBuilder();
        char[] chars = input.toCharArray();

        int cursor = 0;
        int windowSize = 1;

        while (cursor < input.length()) {
            if (cursor + windowSize < input.length() && chars[cursor] == chars[cursor + windowSize]) {
                windowSize++;
            } else {
                sb.append(windowSize);
                sb.append(chars[cursor]);
                cursor += windowSize;
                windowSize = 1;
            }
        }

        return iterations > 1 ? execute(sb.toString(), --iterations) : sb.toString();
    }
}
