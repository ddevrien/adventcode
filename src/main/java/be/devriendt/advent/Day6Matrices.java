package be.devriendt.advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dennis on 14/12/2015.
 */
public class Day6Matrices {

    private static final String TURN_ON = "turn on";
    private static final String TURN_OFF = "turn off";
    private static final String TOGGLE = "toggle";

    private static final Pattern PATTERN = Pattern.compile(".* (\\d+),(\\d+) through (\\d+),(\\d+)$");

    public static int getNumberOfLightsOnFromInstructions(String resourcePath) throws URISyntaxException, IOException {
        return matrixToNumberOfLightsOn(
                Files.readAllLines(Paths.get(Day6Matrices.class.getResource(resourcePath).toURI())),
                new int[1000][1000]);
    }

    public static int getLightBrightnessFromInstructions(String resourcePath) throws URISyntaxException, IOException {
        return matrixToLightsBrightness(
                Files.readAllLines(Paths.get(Day6Matrices.class.getResource(resourcePath).toURI())),
                new int[1000][1000]);
    }

    public static int matrixToNumberOfLightsOn(List<String> instructions, int[][] initialState) {
        return matrixToSum(Mode.TOGGLE, instructions, initialState);
    }

    public static int matrixToLightsBrightness(List<String> instructions, int[][] initialState) {
        return matrixToSum(Mode.ADDITIVE, instructions, initialState);
    }

    private static int matrixToSum(Mode mode, List<String> instructions, int[][] initialState) {
        for (String instruction: instructions) {
            executeInstruction(mode, instruction, initialState);
        }

        int total = 0;
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                total += initialState[i][j];
            }
        }

        return total;
    }

    private static void executeInstruction(Mode mode, String instructionLine, int[][] stateMatrix) {
        Instruction instruction = null;

        if (mode == Mode.TOGGLE && instructionLine.startsWith(TURN_ON)) {
            instruction = Instruction.ON;
        } else if (mode == Mode.TOGGLE && instructionLine.startsWith(TURN_OFF)) {
            instruction = Instruction.OFF;
        } else if (mode == Mode.TOGGLE && instructionLine.startsWith(TOGGLE)) {
            instruction = Instruction.SWITCH;
        } else if (mode == Mode.ADDITIVE && instructionLine.startsWith(TURN_ON)) {
            instruction = Instruction.BRIGHTER;
        } else if (mode == Mode.ADDITIVE && instructionLine.startsWith(TURN_OFF)) {
            instruction = Instruction.LESS_BRIGHT;
        } else if (mode == Mode.ADDITIVE && instructionLine.startsWith(TOGGLE)) {
            instruction = Instruction.BRIGHTER_DOUBLE;
        }

        Matcher m = PATTERN.matcher(instructionLine);

        if (m.find()) {
            int a = Integer.parseInt(m.group(1));
            int b = Integer.parseInt(m.group(2));
            int x = Integer.parseInt(m.group(3));
            int y = Integer.parseInt(m.group(4));

            switch (instruction) {
                case ON:
                    for (int i = a; i <= x; i++) {
                        for (int j = b; j <= y; j++) {
                            stateMatrix[i][j] = 1;
                        }
                    }
                    break;
                case OFF:
                    for (int i = a; i <= x; i++) {
                        for (int j = b; j <= y; j++) {
                            stateMatrix[i][j] = 0;
                        }
                    }
                    break;
                case SWITCH:
                    for (int i = a; i <= x; i++) {
                        for (int j = b; j <= y; j++) {
                            stateMatrix[i][j] = stateMatrix[i][j] == 0 ? 1 : 0;
                        }
                    }
                    break;
                case BRIGHTER:
                    for (int i = a; i <= x; i++) {
                        for (int j = b; j <= y; j++) {
                            stateMatrix[i][j]++;
                        }
                    }
                    break;
                case LESS_BRIGHT:
                    for (int i = a; i <= x; i++) {
                        for (int j = b; j <= y; j++) {
                            stateMatrix[i][j] = stateMatrix[i][j] > 0 ? stateMatrix[i][j] - 1 : 0;
                        }
                    }
                    break;
                case BRIGHTER_DOUBLE:
                    for (int i = a; i <= x; i++) {
                        for (int j = b; j <= y; j++) {
                            stateMatrix[i][j] += 2;
                        }
                    }
                    break;
            }
        }
    }

    private enum Instruction {
        ON, OFF, SWITCH, BRIGHTER, LESS_BRIGHT, BRIGHTER_DOUBLE
    }

    private enum Mode {
        TOGGLE, ADDITIVE
    }
}
