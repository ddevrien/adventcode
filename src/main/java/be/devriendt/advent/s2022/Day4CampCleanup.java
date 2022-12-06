package be.devriendt.advent.s2022;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Day4CampCleanup {

    public static int findNumberOfFullyContainedSections(List<String> input) {
        return (int) input.stream()
                .map(Bounds::fromInput)
                .filter(bounds -> (bounds.low1 <= bounds.low2 && bounds.up1 >= bounds.up2) ||
                            (bounds.low1 >= bounds.low2 && bounds.up1 <= bounds.up2))
                .count();
    }

    public static int findNumberOfPartiallyContainedSections(List<String> input) {
        return (int) input.stream()
                .map(Bounds::fromInput)
                .filter(bounds -> (bounds.up1 >= bounds.low2) && (bounds.low1 <= bounds.up2))
                .count();
    }

    private static class Bounds {

        public final int low1;
        public final int up1;

        public final int low2;
        public final int up2;

        private Bounds(int low1, int up1, int low2, int up2) {
            this.low1 = low1;
            this.up1 = up1;
            this.low2 = low2;
            this.up2 = up2;
        }

        public static Bounds fromInput(String line) {
            String[] split = line.split(",");
            String firstElf = split[0];
            String secondElf = split[1];

            String[] bounds = firstElf.split("-");
            int firstLowerBound = parseInt(bounds[0]);
            int firstUpperBound = parseInt(bounds[1]);
            bounds = secondElf.split("-");
            int secondLowerBound = parseInt(bounds[0]);
            int secondUpperBound = parseInt(bounds[1]);

            return new Bounds(firstLowerBound, firstUpperBound, secondLowerBound, secondUpperBound);
        }
    }
}
