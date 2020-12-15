package be.devriendt.advent.s2020;

import java.util.HashMap;
import java.util.List;

public class Day15RambunctiousRecitation {

    private static class TurnMemory {
        Integer turnLastSpoken;
        Integer turnLastSpokenBeforeThat;

        public TurnMemory(Integer turnLastSpoken) {
            this.turnLastSpoken = turnLastSpoken;
        }

        public void update(int turn) {
            turnLastSpokenBeforeThat = turnLastSpoken;
            turnLastSpoken = turn;
        }

        public boolean isSpokenForFirstTime() {
            return turnLastSpokenBeforeThat == null;
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
        HashMap<Integer, TurnMemory> counters = new HashMap<>(turns/6); // init it big enough
        int lastNumberSpoken = -1;

        for (int turn = 1; turn <= turns; turn++) {
            if (turn <= startingNumbers.size()) {
                lastNumberSpoken = startingNumbers.get(turn - 1);
                counters.put(lastNumberSpoken, new TurnMemory(turn));
            } else {
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
        }

        return lastNumberSpoken;
    }

}
