package be.devriendt.advent.s2020;

import java.util.List;

public class Day17ConwayCubes {

    public static int findNumberOfActiveCubes(List<String> input, int cycles) {
        boolean[][][] dimension = initDimension(input);

        for (int i = 0; i < cycles; i++) {
//            printDimension(dimension);
            dimension = doCycle(dimension);
        }

//        printDimension(dimension);

        return countActiveCubes(dimension);
    }

    private static boolean[][][] doCycle(boolean[][][] dimension) {
        int size = dimension.length + 2;
        boolean[][][] newDimension = new boolean[size][size][size];

        for (int z = 0; z < size; z++) {
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    int activeNeighbours = getActiveNeighbours(dimension, x, y, z);
                    boolean previousCycle = getPreviousCycleSafely(dimension, x, y, z);
                    if (previousCycle && (activeNeighbours ==  2 || activeNeighbours == 3)) {
                        newDimension[x+1][y+1][z+1] = true;
                    } else if (!previousCycle && activeNeighbours == 3) {
                        newDimension[x+1][y+1][z+1] = true;
                    }
                }
            }
        }

        return newDimension;
    }

    private static boolean getPreviousCycleSafely(boolean[][][] dimension, int x, int y, int z) {
        int size = dimension.length;
        if (x < size && y < size && z < size) {
            return dimension[x][y][z];
        }
        return false;
    }

    private static int getActiveNeighbours(boolean[][][] dimension, int x, int y, int z) {
        int size = dimension.length;
        int activeNeighbours = 0;
        for (int zi = Math.max(z - 1, 0); zi <= Math.min(z + 1, size - 1); zi++) {
            for (int yi = Math.max(y - 1, 0); yi <= Math.min(y + 1, size - 1); yi++) {
                for (int xi = Math.max(x - 1, 0); xi <= Math.min(x + 1, size - 1); xi++) {
                    if (!(xi == x && yi == y && zi == z) && dimension[xi][yi][zi]) {
                        activeNeighbours++;
                    }
                }
            }
        }
        return activeNeighbours;
    }

    private static boolean[][][] initDimension(List<String> input) {
        int size = input.get(0).length() + 2;
        boolean[][][] dimension = new boolean[size][size][size];

        for (int y = 0; y < input.size(); y++) {
            char[] line = input.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                dimension[x+1][y+1][size/2] = line[x] == '#';
            }
        }

        return dimension;
    }

    private static int countActiveCubes(boolean[][][] dimension) {
        int sum = 0;
        int size = dimension.length;
        for (int z = 0; z < size; z++) {
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (dimension[x][y][z]) {
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    private static void printDimension(boolean[][][] dimension) {
        int size = dimension.length;
        for (int z = 0; z < size; z++) {
            System.out.println("z: " + (z-size/2));
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (dimension[x][y][z]) {
                        System.out.print('#');
                    } else {
                        System.out.print('.');
                    }
                }
                System.out.println();
            }
        }
        System.out.println("----------------------");
    }

    public static int findNumberOfActiveCubesInHyperDimension(List<String> input, int cycles) {
        boolean[][][][] dimension = initHyperDimension(input);

        for (int i = 0; i < cycles; i++) {
            dimension = doCycle(dimension);
        }

        return countActiveCubes(dimension);
    }

    private static boolean[][][][] initHyperDimension(List<String> input) {
        int size = input.get(0).length() + 2;
        boolean[][][][] dimension = new boolean[size][size][size][size];

        for (int y = 0; y < input.size(); y++) {
            char[] line = input.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                dimension[x+1][y+1][size/2][size/2] = line[x] == '#';
            }
        }

        return dimension;
    }

    private static boolean[][][][] doCycle(boolean[][][][] dimension) {
        int size = dimension.length + 2;
        boolean[][][][] newDimension = new boolean[size][size][size][size];

        for (int w = 0; w < size; w++) {
            for (int z = 0; z < size; z++) {
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        int activeNeighbours = getActiveNeighbours(dimension, x, y, z, w);
                        boolean previousCycle = getPreviousCycleSafely(dimension, x, y, z, w);
                        if (previousCycle && (activeNeighbours == 2 || activeNeighbours == 3)) {
                            newDimension[x + 1][y + 1][z + 1][w + 1] = true;
                        } else if (!previousCycle && activeNeighbours == 3) {
                            newDimension[x + 1][y + 1][z + 1][w + 1] = true;
                        }
                    }
                }
            }
        }

        return newDimension;
    }

    private static int getActiveNeighbours(boolean[][][][] dimension, int x, int y, int z, int w) {
        int size = dimension.length;
        int activeNeighbours = 0;

        for (int wi = Math.max(w - 1, 0); wi <= Math.min(w + 1, size - 1); wi++) {
            for (int zi = Math.max(z - 1, 0); zi <= Math.min(z + 1, size - 1); zi++) {
                for (int yi = Math.max(y - 1, 0); yi <= Math.min(y + 1, size - 1); yi++) {
                    for (int xi = Math.max(x - 1, 0); xi <= Math.min(x + 1, size - 1); xi++) {
                        if (!(xi == x && yi == y && zi == z && wi == w) && dimension[xi][yi][zi][wi]) {
                            activeNeighbours++;
                        }
                    }
                }
            }
        }

        return activeNeighbours;
    }

    private static boolean getPreviousCycleSafely(boolean[][][][] dimension, int x, int y, int z, int w) {
        int size = dimension.length;
        if (x < size && y < size && z < size && w < size) {
            return dimension[x][y][z][w];
        }
        return false;
    }

    private static int countActiveCubes(boolean[][][][] dimension) {
        int sum = 0;
        int size = dimension.length;
        for (int w = 0; w < size; w++) {
            for (int z = 0; z < size; z++) {
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        if (dimension[x][y][z][w]) {
                            sum++;
                        }
                    }
                }
            }
        }

        return sum;
    }
}
