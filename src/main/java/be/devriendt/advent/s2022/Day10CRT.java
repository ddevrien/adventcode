package be.devriendt.advent.s2022;

import java.util.List;

public class Day10CRT {

    private static final int FIRST_CYCLE_TO_CHECK = 20;
    private static final int LAST_CYCLE_TO_CHECK = 220;
    private static final int INTERVAL_CYCLE_TO_CHECK = 40;

    public static int findSignalStrengthSum(List<String> input) {
        CPU cpu = new CPU(input);
        cpu.run();
        return cpu.signalStrengthSum;
    }

    public static String renderScreen(List<String> input) {
        CRT crt = new CRT();
        CPU cpu = new CPU(input, crt);
        cpu.run();
        return crt.render();
    }

    private static class CPU {

        boolean running = false;
        int cycleCount = 0;
        int pc = 0;
        int registerX = 1;

        List<String> instructions;
        private Instruction curInstruction;

        private int signalStrengthSum = 0;
        private CRT crt;

        public CPU(List<String> instructions) {
            this.instructions = instructions;
        }

        public CPU(List<String> instructions, CRT crt) {
            this.instructions = instructions;
            this.crt = crt;
        }


        public void run() {
            running = true;
            readNextInstruction();

            while (running) {
                cycleCount++;

                if (cycleCount % INTERVAL_CYCLE_TO_CHECK == FIRST_CYCLE_TO_CHECK) {
                    signalStrengthSum += registerX * cycleCount;
                }

                if (crt != null) {
                    crt.draw(cycleCount, registerX);
                }

                boolean isDone = curInstruction.tick();

                if (isDone) {
                    if (shouldContinue()) {
                        readNextInstruction();
                    } else {
                        running = false;
                    }
                }
            }
        }

        private boolean shouldContinue() {
            return (crt != null || cycleCount < LAST_CYCLE_TO_CHECK) && pc < instructions.size();
        }

        private void readNextInstruction() {
            String[] parts = instructions.get(pc).split(" ");
            curInstruction = switch (parts[0].toUpperCase()) {
                case "NOOP" -> new Noop();
                case "ADDX" -> new Addx(Integer.parseInt(parts[1]), this);
                default -> throw new IllegalArgumentException("Unknown op");
            };
            pc++;
        }
    }

    private static abstract class Instruction {
        String op;
        int operand;
        int cyclesNeeded;
        int cyclesBusy = 0;

        private Instruction(String op, int operand, int cyclesNeeded) {
            this.op = op;
            this.operand = operand;
            this.cyclesNeeded = cyclesNeeded;
        }

        protected boolean tick() {
            cyclesBusy++;
            return cyclesBusy == cyclesNeeded;
        }
    }

    private static class Noop extends Instruction {

        public Noop() {
            super("NOOP", 0, 1);
        }

    }

    private static class Addx extends Instruction {

        private CPU cpu;

        public Addx(int operand, CPU cpu) {
            super("ADDX", operand, 2);
            this.cpu = cpu;
        }

        @Override
        protected boolean tick() {
            boolean isDone = super.tick();
            if (isDone) {
                cpu.registerX += operand;
            }
            return isDone;
        }
    }

    private static class CRT {
        private StringBuilder screen = new StringBuilder();

        public void draw(int cycleCount, int spritePos) {
            int px = (cycleCount - 1) % 40;
            if (px >= spritePos - 1 && px <= spritePos + 1) {
                screen.append('#');
            } else {
                screen.append('.');
            }
        }

        public String render() {
            return screen.toString()
                    .replaceAll("(.{40})", "$1\n")
                    .trim();
        }
    }

}
