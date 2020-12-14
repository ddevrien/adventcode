package be.devriendt.advent.s2020;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

        public int getBusId() {
            return busId;
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
        List<BusWithOffset> busses = new ArrayList<>(busIds.length);

        for (int i = 0; i < busIds.length; i++) {
            if (!busIds[i].equals("x")) {
                BusWithOffset busWithOffset = new BusWithOffset(Integer.parseInt(busIds[i]), i);
                busses.add(busWithOffset);
            }
        }

        busses.sort(Comparator.comparing(BusWithOffset::getBusId).reversed());
        BusWithOffset busWithHighestId = busses.get(0);
        long earliestSolution = -busWithHighestId.offset;
        boolean solution = false;

        while (!solution) {
            earliestSolution += busWithHighestId.busId;

            for (int i = 0; i < busses.size(); i++) {
                BusWithOffset nextBus = busses.get(i);
                if ((earliestSolution + nextBus.offset) % nextBus.busId != 0) {
                    break;
                } else if (i == busses.size() -1) {
                    solution = true;
                }
            }
        }

        return earliestSolution;
    }

    public static long getMatchingDepartureForBussesParallel(List<String> input) {
        String[] busIds = input.get(1).split(",");
        final List<BusWithOffset> busses = new ArrayList<>(busIds.length);

        for (int i = 0; i < busIds.length; i++) {
            if (!busIds[i].equals("x")) {
                BusWithOffset busWithOffset = new BusWithOffset(Integer.parseInt(busIds[i]), i);
                busses.add(busWithOffset);
            }
        }

        busses.sort(Comparator.comparing(BusWithOffset::getBusId).reversed());
        BusWithOffset busWithHighestId = busses.get(0);

        OptionalLong first = LongStream.iterate(-busWithHighestId.offset, i -> i + busWithHighestId.busId)
                .parallel()
                .filter(candidate -> {
                    for (int i = 0; i < busses.size(); i++) {
                        BusWithOffset nextBus = busses.get(i);
                        if ((candidate + nextBus.offset) % nextBus.busId != 0) {
                            return false;
                        } else if (i == busses.size() - 1) {
                            return true;
                        }
                    }
                    return false;
                })
                .findFirst();

        return first.getAsLong();
    }
}
