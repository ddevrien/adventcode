package be.devriendt.advent.s2021;

import java.util.List;

public class Day2RockPaperScissors {

    public static int executeWithStrategy(List<String> strategy) {
        return strategy.stream()
                .map(Instruction::fromPart1)
                .map(Instruction::getScore)
                .reduce(0, Integer::sum);
    }

    public static int executeWithStrategy2(List<String> strategy) {
        return strategy.stream()
                .map(Instruction::fromPart2)
                .map(Instruction::getScore)
                .reduce(0, Integer::sum);
    }

    private enum Rsp {
        ROCK, PAPER, SCISSORS;

        private static Rsp opponent(char c) {
            return switch (c) {
                case 'A' -> ROCK;
                case 'B' -> PAPER;
                case 'C' -> SCISSORS;
                default -> throw new IllegalArgumentException("Unknown");
            };
        }

        private static Rsp mePart1(char c) {
            return switch (c) {
                case 'X' -> ROCK;
                case 'Y' -> PAPER;
                case 'Z' -> SCISSORS;
                default -> throw new IllegalArgumentException("Unknown");
            };
        }

        private static Rsp mePart2(char opponent, char me) {
            return switch (me) {
                case 'X' -> switch (opponent) {
                    case 'A' -> SCISSORS;
                    case 'B' -> ROCK;
                    case 'C' -> PAPER;
                    default -> throw new IllegalArgumentException("Unknown");
                };
                case 'Y' -> switch (opponent) {
                    case 'A' -> ROCK;
                    case 'B' -> PAPER;
                    case 'C' -> SCISSORS;
                    default -> throw new IllegalArgumentException("Unknown");
                };
                case 'Z' -> switch (opponent) {
                    case 'A' -> PAPER;
                    case 'B' -> SCISSORS;
                    case 'C' -> ROCK;
                    default -> throw new IllegalArgumentException("Unknown");
                };
                default -> throw new IllegalArgumentException("Unknown");
            };
        }
    }

    private static class Instruction {
        private Rsp opponent;
        private Rsp me;

        private Instruction(Rsp opponent, Rsp me) {
            this.opponent = opponent;
            this.me = me;
        }

        public static Instruction fromPart1(String line) {
            Rsp opponent = Rsp.opponent(line.charAt(0));
            Rsp me = Rsp.mePart1(line.charAt(2));
            return new Instruction(opponent, me);
        }

        public static Instruction fromPart2(String line) {
            Rsp opponent = Rsp.opponent(line.charAt(0));
            Rsp me = Rsp.mePart2(line.charAt(0), line.charAt(2));
            return new Instruction(opponent, me);
        }

        public int getScore() {
            return switch (me) {
                case ROCK -> 1 +
                        switch (opponent) {
                            case ROCK -> 3;
                            case PAPER -> 0;
                            case SCISSORS -> 6;
                        };
                case PAPER -> 2 +
                        switch (opponent) {
                            case ROCK -> 6;
                            case PAPER -> 3;
                            case SCISSORS -> 0;
                        };
                case SCISSORS -> 3 +
                        switch (opponent) {
                            case ROCK -> 0;
                            case PAPER -> 6;
                            case SCISSORS -> 3;
                        };
            };
        }
    }
}
