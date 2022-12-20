package be.devriendt.advent.s2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Day15BeaconExclusionZone {

    private static final long TUNING_FREQ_MULTIPLIER = 4000000L;

    public static int findNrOfPositionsWhereBeaconCannotBePresent(List<String> input, int row) {
        Map map = Map.fromInput(input);
        Range maxMergedRange = findMaxMergedRange(map, row);
        return Math.abs(maxMergedRange.start - maxMergedRange.end);
    }

    public static long findBeaconPosition(List<String> input, int min, int max) {
        Map map = Map.fromInput(input);

        for (int row = min; row <= max; row++) {
            int position = findMaxMergedRange(map, row).end + 1;
            if (position < max) {
                return row + (position * TUNING_FREQ_MULTIPLIER);
            }
        }

        return -1;
    }

    private static Range findMaxMergedRange(Map map, int row) {
        List<Range> ranges = new ArrayList<>();

        map.sensors.stream()
                .forEach(sensor -> {
                    Integer left = sensor.getLeftMostCoveredPointOnRow(row);
                    Integer right = sensor.getRightMostCoveredPointOnRow(row);
                    if (left != null && right != null) {
                        Range sensorRange = new Range(left, right);
                        ranges.stream()
                                .filter(r -> r.overlaps(sensorRange))
                                .findFirst()
                                .ifPresentOrElse(r -> r.merge(sensorRange),
                                        () -> ranges.add(sensorRange));
                    }
                });

        Collections.sort(ranges, Comparator.comparing(Range::getStart));

        Range mergedRange = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++) {
            Range checkRange = ranges.get(i);
            if (mergedRange.overlaps(checkRange)) {
                mergedRange.merge(checkRange);
            }
        }

        return mergedRange;
    }

    private static class Map {
        List<Sensor> sensors;
        Set<Beacon> beacons;

        public Map(List<Sensor> sensors, Set<Beacon> beacons) {
            this.sensors = sensors;
            this.beacons = beacons;
        }

        public static Map fromInput(List<String> input) {
            Set<Sensor> sensors = new TreeSet<>(Comparator.comparing(sensor -> sensor.distanceToClosestBeacon, Comparator.reverseOrder()));
            Set<Beacon> beacons = new HashSet<>();

            for (String line: input) {
                String[] parts = line.split(":");
                Sensor sensor = (Sensor) Point.from(parts[0]);
                Beacon beacon = (Beacon) Point.from(parts[1]);
                sensors.add(sensor);
                beacons.add(beacon);
                sensor.setClosestBeacon(beacon);
            }

            return new Map(sensors.stream().toList(), beacons);
        }
    }

    private static class Point {
        int x;
        int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point from(String input) {
            String[] parts = input.split("(=|,)");
            int xCo = Integer.parseInt(parts[1]);
            int yCo = Integer.parseInt(parts[3]);
            return parts[0].contains("Sensor") ? new Sensor(xCo, yCo) : new Beacon(xCo, yCo);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Sensor extends Point {
        private Beacon closestBeacon;
        private int distanceToClosestBeacon;

        public Sensor(int x, int y) {
            super(x, y);
        }

        public void setClosestBeacon(Beacon closestBeacon) {
            this.closestBeacon = closestBeacon;
            this.distanceToClosestBeacon = Math.abs(this.x - closestBeacon.x) + Math.abs(this.y - closestBeacon.y);
        }

        public Integer getLeftMostCoveredPointOnRow(int row) {
            int distanceToRow = Math.abs(this.y - row);
            int diff = distanceToClosestBeacon - distanceToRow;
            return diff > 0 ? x - diff : null;
        }

        public Integer getRightMostCoveredPointOnRow(int row) {
            int distanceToRow = Math.abs(this.y - row);
            int diff = distanceToClosestBeacon - distanceToRow;
            return diff > 0 ? x + diff : null;
        }

        @Override
        public String toString() {
            return "Sensor{" +
                    "x=" + x +
                    ", y=" + y +
                    ", closestBeacon=" + closestBeacon +
                    '}';
        }
    }

    private static class Beacon extends Point {

        public Beacon(int x, int y) {
            super(x, y);
        }

        @Override
        public String toString() {
            return "Beacon{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean overlaps(Range other) {
            return (other.start >= start && other.start <= end) || (start >= other.start && start <= other.end);
        }

        public void merge(Range other) {
            start = Math.min(start, other.start);
            end = Math.max(end, other.end);
        }

        public int getStart() {
            return start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return start == range.start && end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "R[" + start + "-" + end + ']';
        }
    }
}
