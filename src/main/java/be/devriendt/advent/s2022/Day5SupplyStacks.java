package be.devriendt.advent.s2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5SupplyStacks {

    public static String findTopOfStackCrates(List<String> input) {
        return findTopOfStackCrates(input, false);
    }

    public static String findTopOfStackCrates(List<String> input, boolean multiPickup) {
        int indexOfStackIndicators = -1;
        while (!input.get(indexOfStackIndicators+1).isEmpty()) {
            indexOfStackIndicators++;
        }

        List<String> stacks = getInitialStacks(input, indexOfStackIndicators);

        for (int i = indexOfStackIndicators + 2; i < input.size(); i++) {
            String[] instructionParts = input.get(i).split(" ");
            int count = Integer.parseInt(instructionParts[1]);
            int from = Integer.parseInt(instructionParts[3]) - 1;
            int to = Integer.parseInt(instructionParts[5]) - 1;

            String fromStack = stacks.get(from);
            StringBuilder cratesToMove = new StringBuilder(fromStack.substring(fromStack.length() - count));
            stacks.set(from, fromStack.substring(0, fromStack.length() - count));
            stacks.set(to, stacks.get(to) + (multiPickup ? cratesToMove : cratesToMove.reverse()));
        }

        StringBuilder sb = new StringBuilder();

        stacks.stream()
                .map(stack -> stack.charAt(stack.length() - 1))
                .forEach(sb::append);

        return sb.toString();
    }

    private static List<String> getInitialStacks(List<String> input, int indexOfStackIndicators) {
        String[] parts = input.get(indexOfStackIndicators).trim().split(" ");
        int nrOfStacks = Integer.parseInt(parts[parts.length - 1]);

        List<String> stacks = new ArrayList<>(Collections.nCopies(nrOfStacks, ""));
        for (int s = indexOfStackIndicators - 1; s >= 0; s--) {
            String line = input.get(s);
            for (int i = 0; i < nrOfStacks && (i * 4 + 1) < line.length(); i++) {
                String curStack = stacks.get(i);
                char curCrate = line.charAt(i * 4 + 1);
                if (curCrate != ' ') {
                    stacks.set(i, curStack + curCrate);
                }
            }
        }

        return stacks;
    }
}
