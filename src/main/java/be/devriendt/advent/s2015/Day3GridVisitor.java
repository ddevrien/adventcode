package be.devriendt.advent.s2015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dennis on 5/12/2015.
 */
public class Day3GridVisitor {

    private static final char NORTH = '^';

    private static final char SOUTH = 'v';

    private static final char EAST = '>';

    private static final char WEST = '<';

    public static Map<Coordinate, AtomicInteger> directionsToVisitCounts(String input) {
        Map<Coordinate, AtomicInteger> visitCounts = new HashMap<>();
        Coordinate currCoordinate = new Coordinate(0, 0);
        visitCounts.put(currCoordinate, new AtomicInteger(1));

        for (char c: input.toCharArray()) {
            currCoordinate = getUpdatedCoordinate(currCoordinate, c);
            visitCounts.putIfAbsent(currCoordinate, new AtomicInteger(0));
            visitCounts.get(currCoordinate).incrementAndGet();
        }

        return visitCounts;
    }

    public static Map<Coordinate, AtomicInteger> directionsDoubleTeamToVisitCounts(String input) {
        Map<Coordinate, AtomicInteger> visitCounts = new HashMap<>();
        visitCounts.put(new Coordinate(0, 0), new AtomicInteger(1));

        Coordinate santa = new Coordinate(0, 0);
        Coordinate robosanta = new Coordinate(0, 0);

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i += 2) {
            char cSanta = chars[i];
            santa = getUpdatedCoordinate(santa, cSanta);
            visitCounts.putIfAbsent(santa, new AtomicInteger(0));
            visitCounts.get(santa).incrementAndGet();

            if (i+1 < chars.length) {
                char cRobosanta = chars[i+1];
                robosanta = getUpdatedCoordinate(robosanta, cRobosanta);
                visitCounts.putIfAbsent(robosanta, new AtomicInteger(0));
                visitCounts.get(robosanta).incrementAndGet();
            }
        }

        return visitCounts;
    }

    private static Coordinate getUpdatedCoordinate(Coordinate currCoordinate, char direction) {
        switch (direction) {
            case NORTH:
                return currCoordinate.north();
            case SOUTH:
                return currCoordinate.south();
            case EAST:
                return currCoordinate.east();
            case WEST:
                return currCoordinate.west();
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    private static class Coordinate {

        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate north() {
            return new Coordinate(x+1, y);
        }

        public Coordinate south() {
            return new Coordinate(x-1, y);
        }

        public Coordinate east() {
            return new Coordinate(x, y+1);
        }

        public Coordinate west() {
            return new Coordinate(x, y-1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            return y == that.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
