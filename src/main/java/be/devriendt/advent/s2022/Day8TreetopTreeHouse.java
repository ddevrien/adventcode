package be.devriendt.advent.s2022;

import java.util.List;

public class Day8TreetopTreeHouse {

    public static int getNumberOfVisibleTrees(List<String> input) {
        int[][] forest = initForest(input);
        int visibleTrees = 0;

        for (int x = 0; x < forest.length; x++) {
            for (int y = 0; y < forest[x].length; y++) {
                if (isVisible(forest, x, y)) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees;
    }

    public static int findBestTree(List<String> input) {
        int[][] forest = initForest(input);
        int maxScenicScore = 0;

        for (int x = 0; x < forest.length; x++) {
            for (int y = 0; y < forest[x].length; y++) {
                maxScenicScore = Math.max(maxScenicScore, findScenicScore(forest, x, y));
            }
        }

        return maxScenicScore;
    }

    private static boolean isVisible(int[][] forest, int x, int y) {
        int treeHeight = forest[x][y];
        if (x == 0 || y == 0 || x == forest.length - 1 || y == forest[x].length - 1) {
            return true;
        }

        return visibleFromLeft(forest, x, y, treeHeight) || visibleFromRight(forest, x, y, treeHeight)
                || visibleFromTop(forest, x, y, treeHeight) || visibleFromBottom(forest, x, y, treeHeight);
    }

    private static boolean visibleFromLeft(int[][] forest, int x, int y, int treeHeight) {
        for (int i = 0; i < y; i++) {
            if (forest[x][i] >= treeHeight) {
                return false;
            }
        }
        return true;
    }

    private static boolean visibleFromRight(int[][] forest, int x, int y, int treeHeight) {
        for (int i = y + 1; i < forest[x].length; i++) {
            if (forest[x][i] >= treeHeight) {
                return false;
            }
        }
        return true;
    }

    private static boolean visibleFromTop(int[][] forest, int x, int y, int treeHeight) {
        for (int i = 0; i < x; i++) {
            if (forest[i][y] >= treeHeight) {
                return false;
            }
        }
        return true;
    }

    private static boolean visibleFromBottom(int[][] forest, int x, int y, int treeHeight) {
        for (int i = x + 1; i < forest.length; i++) {
            if (forest[i][y] >= treeHeight) {
                return false;
            }
        }
        return true;
    }

    private static int findScenicScore(int[][] forest, int x, int y) {
        int treeHeight = forest[x][y];
        if (x == 0 || y == 0 || x == forest.length - 1 || y == forest[x].length - 1) {
            return 0;
        }

        return visibleTreesFromLeft(forest, x, y, treeHeight) * visibleTreesFromRight(forest, x, y, treeHeight)
                * visibleTreesFromTop(forest, x, y, treeHeight) * visibleTreesFromBottom(forest, x, y, treeHeight);
    }

    private static int visibleTreesFromLeft(int[][] forest, int x, int y, int treeHeight) {
        int visibleTrees = 0;
        for (int i = y - 1; i >= 0; i--) {
            visibleTrees++;
            if (forest[x][i] >= treeHeight) {
                return visibleTrees;
            }
        }
        return visibleTrees;
    }

    private static int visibleTreesFromRight(int[][] forest, int x, int y, int treeHeight) {
        int visibleTrees = 0;
        for (int i = y + 1; i < forest[x].length; i++) {
            visibleTrees++;
            if (forest[x][i] >= treeHeight) {
                return visibleTrees;
            }
        }
        return visibleTrees;
    }

    private static int visibleTreesFromTop(int[][] forest, int x, int y, int treeHeight) {
        int visibleTrees = 0;
        for (int i = x - 1; i >= 0; i--) {
            visibleTrees++;
            if (forest[i][y] >= treeHeight) {
                return visibleTrees;
            }
        }
        return visibleTrees;
    }

    private static int visibleTreesFromBottom(int[][] forest, int x, int y, int treeHeight) {
        int visibleTrees = 0;
        for (int i = x + 1; i < forest.length; i++) {
            visibleTrees++;
            if (forest[i][y] >= treeHeight) {
                return visibleTrees;
            }
        }
        return visibleTrees;
    }

    private static int[][] initForest(List<String> input) {
        int[][] forest = new int[input.size()][input.get(0).length()];
        for (int x = 0; x < input.size(); x++) {
            String curLine = input.get(x);
            for (int y = 0; y < curLine.length(); y++) {
                forest[x][y] = curLine.charAt(y) - '0';
            }
        }
        return forest;
    }
}
