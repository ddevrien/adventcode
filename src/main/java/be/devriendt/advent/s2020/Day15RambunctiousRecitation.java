package be.devriendt.advent.s2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15RambunctiousRecitation {

    private static class TurnMemory {
        int turnLastSpoken;
        int turnLastSpokenBeforeThat = -1;

        public TurnMemory(int turnLastSpoken) {
            this.turnLastSpoken = turnLastSpoken;
        }

        public void update(int turn) {
            turnLastSpokenBeforeThat = turnLastSpoken;
            turnLastSpoken = turn;
        }

        public boolean isSpokenForFirstTime() {
            return turnLastSpokenBeforeThat == -1;
        }

        public int getDifference() {
            return turnLastSpoken - turnLastSpokenBeforeThat;
        }

        @Override
        public String toString() {
            return "[" + turnLastSpoken + "," + turnLastSpokenBeforeThat + "]";
        }

    }

    public static int getNumberSpokenOnTurn(List<Integer> startingNumbers, int turns) {
        Map<Integer, TurnMemory> counters = new HashMap<>(turns/6); // init it big enough
        Integer lastNumberSpoken = -1;

        for (int turn = 1; turn <= startingNumbers.size(); turn++) {
            lastNumberSpoken = startingNumbers.get(turn - 1);
            counters.put(lastNumberSpoken, new TurnMemory(turn));
        }

        for (int turn = startingNumbers.size() + 1; turn <= turns; turn++) {
            TurnMemory memory = counters.get(lastNumberSpoken);

            if (memory.isSpokenForFirstTime()) {
                lastNumberSpoken = 0;
            } else {
                lastNumberSpoken = memory.getDifference();
            }

            memory = counters.get(lastNumberSpoken);
            if (memory != null) {
                memory.update(turn);
            } else {
                counters.put(lastNumberSpoken, new TurnMemory(turn));
            }
        }

        return lastNumberSpoken;
    }

}
