package be.devriendt.advent.s2022;

import java.util.Arrays;
import java.util.List;

public class Day12HillClimbing {

    private static final char START_MARKER = 'S';
    private static final int START_MARKER_ELEVATION = 'a' - 'a';
    private static final char END_MARKER = 'E';
    private static final int END_MARKER_ELEVATION = 'z' - 'a';

    public static int findNrOfStepsToDestination(List<String> input) {
        Map map = Map.fromInput(input);
        return map.solve();
    }

    public static int findOptimalStepsToDestination(List<String> input) {
        Map map = Map.fromInput(input);
        return map.solveAll();
    }

    private static class Map {
        private Tile start;
        private Tile end;
        private Tile[][] tiles;

        public Map(Tile start, Tile end, Tile[][] tiles) {
            this.start = start;
            this.end = end;
            this.tiles = tiles;
        }

        public int solve() {
            end.followPath(0);
            return start.minStepsRequired;
        }

        public int solveAll() {
            end.followPath(0);
            return Arrays.stream(tiles)
                    .flatMap(Arrays::stream)
                    .filter(t -> t.elevation == 0)
                    .mapToInt(t -> t.minStepsRequired)
                    .min()
                    .getAsInt();
        }

        public static Map fromInput(List<String> input) {
            Tile start = null;
            Tile end = null;
            Tile[][] tiles = new Tile[input.size()][input.get(0).length()];

            for (int x = 0; x < input.size(); x++) {
                String curLine = input.get(x);
                for (int y = 0; y < curLine.length(); y++) {
                    char tileElevation = curLine.charAt(y);
                    Tile tile = new Tile(x, y);
                    if (tileElevation == START_MARKER) {
                        start = tile;
                        tile.elevation = START_MARKER_ELEVATION;
                    } else if (tileElevation == END_MARKER) {
                        end = tile;
                        tile.elevation = END_MARKER_ELEVATION;
                    } else {
                        tile.elevation = tileElevation - 'a';
                    }
                    tiles[x][y] = tile;
                }
            }

            for (int x = 0; x < tiles.length; x++) {
                for (int y = 0; y < tiles[x].length; y++) {
                    Tile tile = tiles[x][y];
                    tile.left = y > 0 ? tiles[x][y-1] : null;
                    tile.right = y < tiles[x].length - 1 ? tiles[x][y+1] : null;
                    tile.up = x > 0 ? tiles[x-1][y] : null;
                    tile.down = x < tiles.length - 1 ? tiles[x+1][y] : null;
                }
            }

            return new Map(start, end, tiles);
        }
    }

    private static class Tile {
        int x;
        int y;
        int elevation;

        Tile left;
        Tile right;
        Tile up;
        Tile down;

        int minStepsRequired = Integer.MAX_VALUE;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void followPath(int currentStepsTaken) {
            if (currentStepsTaken < minStepsRequired) {
                this.minStepsRequired = currentStepsTaken;
                gotoNeighbour(left, currentStepsTaken);
                gotoNeighbour(right, currentStepsTaken);
                gotoNeighbour(up, currentStepsTaken);
                gotoNeighbour(down, currentStepsTaken);
            }
        }

        private void gotoNeighbour(Tile neighbour, int currentStepsTaken) {
            if (neighbour != null && elevation <= neighbour.elevation + 1) {
                neighbour.followPath(currentStepsTaken + 1);
            }
        }

        @Override
        public String toString() {
            return "Tile{" +
                    "x=" + x +
                    ", y=" + y +
                    ", elevation=" + elevation +
                    ", minStepsRequired=" + minStepsRequired +
                    '}';
        }
    }
}
