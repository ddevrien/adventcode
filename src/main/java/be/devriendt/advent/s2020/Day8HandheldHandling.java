package be.devriendt.advent.s2020;

import java.util.List;

public class Day8HandheldHandling {

    public static class InfiniteLoopException extends RuntimeException {
        private final int accumulator;

        public InfiniteLoopException(int accumulator) {
            this.accumulator = accumulator;
        }

        public int getAccumulator() {
            return accumulator;
        }
    }

    public static int findInfiniteLoopFixAndGetAccumulatorValue(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.startsWith("nop")) {
                input.set(i, line.replaceAll("nop", "jmp"));
            } else if (line.startsWith("jmp")) {
                input.set(i, line.replaceAll("jmp", "nop"));
            }

            try {
                return getAccumulatorValue(input);
            } catch (InfiniteLoopException e) {
            }

            input.set(i, line);
        }

        return -1;
    }

    public static int getAccumulatorValue(List<String> input) {
        int cursor = 0;
        int accumulator = 0;
        boolean[] visited = new boolean[input.size()];
        while (cursor < input.size() && !visited[cursor]) {
            String line = input.get(cursor);
            visited[cursor] = true;

            if (line.startsWith("nop")) {
                cursor++;
            } else if (line.startsWith("acc")) {
                accumulator += Integer.parseInt(line.substring(4));
                cursor++;
            } else if (line.startsWith("jmp")) {
                cursor += Integer.parseInt(line.substring(4));
            }
        }

        if (cursor < input.size()) {
            throw new InfiniteLoopException(accumulator);
        }

        return accumulator;
    }
}
