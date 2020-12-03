package be.devriendt.advent.s2020;

import java.util.List;

public class Day3TobogganTrajectory {

    public static class Instruction {
        public final int right;
        public final int down;

        public Instruction(int right, int down) {
            this.right = right;
            this.down = down;
        }
    }

    public static int countNumberOfTrees(String[] input, Instruction instruction) {
        boolean[][] treeMatrix = toTreeMatrix(input);
        return countNumberOfTrees(treeMatrix, instruction);
    }

    public static int countNumberOfTrees(String[] input, List<Instruction> instructions) {
        boolean[][] treeMatrix = toTreeMatrix(input);
        return instructions.stream()
                .mapToInt(instruction -> countNumberOfTrees(treeMatrix, instruction))
                .reduce(1, (a, b) -> a * b);
    }

    private static int countNumberOfTrees(boolean[][] treeMatrix, Instruction instruction) {
        int treesEncountered = 0;
        int xLoc = 0;
        int yLoc = 0;

        while (xLoc < treeMatrix.length) {
            if (treeMatrix[xLoc][yLoc]) {
                treesEncountered++;
            }
            xLoc += instruction.down;
            yLoc = (yLoc + instruction.right) % treeMatrix[0].length;
        }

        return treesEncountered;
    }

    private static boolean[][] toTreeMatrix(String[] input) {
        int rows = input.length;
        int columns = input[0].length();
        boolean[][] treeMatrix = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            char[] row = input[i].toCharArray();

            for (int j = 0; j < columns; j++) {
                treeMatrix[i][j] = row[j] == '#';
            }
        }

        return treeMatrix;
    }
}
