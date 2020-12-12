package be.devriendt.advent.s2020;

import java.util.*;

public class Day10AdapterArray {

    private static final HashMap<Integer, Long> LOOKUP_TABLE = new HashMap<>();

    public static class JoltReport {
        final int diffs;
        final int adapterJoltage;

        public JoltReport(int diffs, int adapterJoltage) {
            this.diffs = diffs;
            this.adapterJoltage = adapterJoltage;
        }
    }

    public static long getJoltagePermutations(List<Integer> input) {
        TreeSet<Integer> set = new TreeSet<>(input);
        int adapterJoltage = getJoltageDifference(input).adapterJoltage;

        LOOKUP_TABLE.clear();
        set.add(0);
        set.add(adapterJoltage);
        set.removeIf(i -> i > adapterJoltage);

        return getJoltagePermutationsRecursive(set, 0, adapterJoltage);
    }

    private static long getJoltagePermutationsRecursive(TreeSet<Integer> input, int cursor, int max) {
        if (cursor == max) {
            return 1;
        }

        if (LOOKUP_TABLE.containsKey(cursor)) {
            return LOOKUP_TABLE.get(cursor);
        }

        long sum = 0;

        if (input.contains(cursor+1)) {
            sum += getJoltagePermutationsRecursive(input, cursor+1, max);
        }
        if (input.contains(cursor+2)) {
            sum += getJoltagePermutationsRecursive(input, cursor+2, max);
        }
        if (input.contains(cursor+3)) {
            sum += getJoltagePermutationsRecursive(input, cursor+3, max);
        }

        LOOKUP_TABLE.put(cursor, sum);
        return sum;
    }

    public static JoltReport getJoltageDifference(List<Integer> input) {
        int joltOneDiffs = 0;
        int joltThreeDiffs = 1; // device adapter always is +3

        TreeSet<Integer> set = new TreeSet<>(input);
        Iterator<Integer> it = set.iterator();
        int cur = 0;

        do {
            int next = it.next();

            switch(next-cur) {
                case 1: joltOneDiffs++; break;
                case 2: break;
                case 3: joltThreeDiffs++; break;
                default:
                    return new JoltReport(joltOneDiffs * joltThreeDiffs, cur + 3);
            }

            cur = next;
        } while (it.hasNext());

        return new JoltReport(joltOneDiffs * joltThreeDiffs, cur + 3);
    }
}
