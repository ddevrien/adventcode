package be.devriendt.advent.s2020;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day13ShuttleSearch {

    private static class BusTimetable {
        final int busId;
        final int earliestDeparture;

        public BusTimetable(int busId, int earliestDeparture) {
            this.busId = busId;
            this.earliestDeparture = earliestDeparture;
        }

        public int getEarliestDeparture() {
            return earliestDeparture;
        }

        public int getSolution(int startingFrom) {
            return busId * (earliestDeparture - startingFrom);
        }
    }

    private static class BusWithOffset {
        int busId;
        int offset;

        public BusWithOffset(int busId, int offset) {
            this.busId = busId;
            this.offset = offset;
        }
    }

    public static int getBusSolution(List<String> input) {
        final int earliestDeparture = Integer.parseInt(input.get(0));

        List<BusTimetable> timetables = Arrays.stream(input.get(1).split(","))
                .filter(busId -> !busId.equals("x"))
                .map(Integer::parseInt)
                .map(busId -> new BusTimetable(busId, ((earliestDeparture / busId) + 1) * busId))
                .sorted(Comparator.comparing(BusTimetable::getEarliestDeparture))
                .collect(Collectors.toList());

        return timetables.get(0).getSolution(earliestDeparture);
    }

    public static long getMatchingDepartureForBusses(List<String> input) {
        String[] busIds = input.get(1).split(",");

        long solution = 0;
        long lcm = Integer.parseInt(busIds[0]);

        for (int i = 1; i < busIds.length; i++) {
            if (!busIds[i].equals("x")) {
                BusWithOffset bus = new BusWithOffset(Integer.parseInt(busIds[i]), i);
                solution = findSolution(solution, lcm, bus);
                lcm = lcm(lcm, bus.busId);
            }
        }

        return solution;
    }

    private static long findSolution(long start, long step, BusWithOffset bus) {
        long solution = start;

        do {
            solution += step;
        } while ((solution + bus.offset) % bus.busId != 0);

        return solution;
    }

    private static long lcm(long a, long b) {
        BigInteger biA =  BigInteger.valueOf(a);
        BigInteger biB =  BigInteger.valueOf(b);
        BigInteger gcd = biA.gcd(biB);
        return biA.multiply(biB).divide(gcd).longValue();
    }

}
