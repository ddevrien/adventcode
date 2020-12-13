package be.devriendt.advent.s2020;

import java.util.Arrays;
import java.util.List;

public class Day11SeatingSystem {

    private static final char FLOOR = '.';
    private static final char EMPTY_SEAT = 'L';
    private static final char OCCUPIED_SEAT = '#';

    private enum Direction {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
    }

    private static final class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(Direction direction) {
            switch (direction) {
                case UP: x -=1; break;
                case DOWN: x +=1; break;
                case LEFT: y -= 1; break;
                case RIGHT: y += 1; break;
                case UP_LEFT: x -= 1; y -= 1; break;
                case UP_RIGHT: x -= 1; y += 1; break;
                case DOWN_LEFT: x += 1; y -= 1; break;
                case DOWN_RIGHT: x += 1; y += 1; break;
            }
        }
    }

    public static int getNrOfOccupiedSeats(List<String> input) {
        char[][] grid = toGrid(input);
        boolean stateChanged;

        do {
            char[][] newGrid = new char[grid.length][grid[0].length];
            stateChanged = false;

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[x].length; y++) {
                    if (emptySeatWithNoAdjacentOccupiedSeats(grid, x, y)) {
                        stateChanged = true;
                        newGrid[x][y] = OCCUPIED_SEAT;
                    } else if (occupiedSeatWithTooManyAdjacentOccupiedSeats(grid, x, y)) {
                        stateChanged = true;
                        newGrid[x][y] = EMPTY_SEAT;
                    } else {
                        newGrid[x][y] = grid[x][y];
                    }
                }
            }

            grid = newGrid;
        } while (stateChanged);

        return getNumberOfOccupiedSeats(grid);
    }

    public static int getNrOfOccupiedSeatsPart2(List<String> input) {
        char[][] grid = toGrid(input);
        boolean stateChanged;

        do {
            char[][] newGrid = new char[grid.length][grid[0].length];
            stateChanged = false;

            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[x].length; y++) {
                    if (emptySeatWithNoOccupiedSeatsInEachDirection(grid, x, y)) {
                        stateChanged = true;
                        newGrid[x][y] = OCCUPIED_SEAT;
                    } else if (occupiedSeatWithTooManyOccupiedSeatsInEachDirection(grid, x, y)) {
                        stateChanged = true;
                        newGrid[x][y] = EMPTY_SEAT;
                    } else {
                        newGrid[x][y] = grid[x][y];
                    }
                }
            }

            grid = newGrid;
        } while (stateChanged);

        return getNumberOfOccupiedSeats(grid);
    }

    private static boolean emptySeatWithNoAdjacentOccupiedSeats(char[][] grid, int x, int y) {
        if (grid[x][y] != EMPTY_SEAT) {
            return false;
        }

        return getSafely(grid, x-1, y) != OCCUPIED_SEAT
                && getSafely(grid, x+1, y) != OCCUPIED_SEAT
                && getSafely(grid, x, y-1) != OCCUPIED_SEAT
                && getSafely(grid, x, y+1) != OCCUPIED_SEAT
                && getSafely(grid, x-1, y-1) != OCCUPIED_SEAT
                && getSafely(grid, x-1, y+1) != OCCUPIED_SEAT
                && getSafely(grid, x+1, y-1) != OCCUPIED_SEAT
                && getSafely(grid, x+1, y+1) != OCCUPIED_SEAT;
    }

    private static boolean occupiedSeatWithTooManyAdjacentOccupiedSeats(char[][] grid, int x, int y) {
        if (grid[x][y] != OCCUPIED_SEAT) {
            return false;
        }

        int occupiedSeats = 0;
        occupiedSeats += getSafely(grid, x-1, y) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x+1, y) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x, y-1) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x, y+1) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x-1, y-1) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x-1, y+1) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x+1, y-1) == OCCUPIED_SEAT ? 1 : 0;
        occupiedSeats += getSafely(grid, x+1, y+1) == OCCUPIED_SEAT ? 1 : 0;

        return occupiedSeats >= 4;
    }

    private static boolean emptySeatWithNoOccupiedSeatsInEachDirection(char[][] grid, int x, int y) {
        if (grid[x][y] != EMPTY_SEAT) {
            return false;
        }

        return !occupiedSeatInDirection(grid, x, y, Direction.LEFT)
                && !occupiedSeatInDirection(grid, x, y, Direction.RIGHT)
                && !occupiedSeatInDirection(grid, x, y, Direction.UP)
                && !occupiedSeatInDirection(grid, x, y, Direction.DOWN)
                && !occupiedSeatInDirection(grid, x, y, Direction.UP_LEFT)
                && !occupiedSeatInDirection(grid, x, y, Direction.UP_RIGHT)
                && !occupiedSeatInDirection(grid, x, y, Direction.DOWN_LEFT)
                && !occupiedSeatInDirection(grid, x, y, Direction.DOWN_RIGHT);
    }

    private static boolean occupiedSeatWithTooManyOccupiedSeatsInEachDirection(char[][] grid, int x, int y) {
        if (grid[x][y] != OCCUPIED_SEAT) {
            return false;
        }

        int occupiedSeats = 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.UP) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.DOWN) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.LEFT) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.RIGHT) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.UP_LEFT) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.UP_RIGHT) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.DOWN_LEFT) ? 1 : 0;
        occupiedSeats += occupiedSeatInDirection(grid, x, y, Direction.DOWN_RIGHT) ? 1 : 0;

        return occupiedSeats >= 5;
    }

    private static boolean occupiedSeatInDirection(char[][] grid, int x, int y, Direction direction) {
        Coordinate coordinate = new Coordinate(x, y);
        coordinate.move(direction);

        while (withinBounds(grid, coordinate.x, coordinate.y)) {
            if (grid[coordinate.x][coordinate.y] == OCCUPIED_SEAT) {
                return true;
            } else if (grid[coordinate.x][coordinate.y] == EMPTY_SEAT) {
                return false;
            }
            coordinate.move(direction);
        }

        return false;
    }

    private static char getSafely(char[][] grid, int x, int y) {
        return withinBounds(grid, x, y) ? grid[x][y] : FLOOR;
    }

    private static boolean withinBounds(char[][] grid, int x, int y) {
        return (x >= 0 && x < grid.length) && (y >= 0 && y < grid[x].length);
    }


    private static char[][] toGrid(List<String> input) {
        return input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private static int getNumberOfOccupiedSeats(char[][] grid) {
        return (int) Arrays.stream(grid)
                .flatMapToInt(row -> new String(row).chars())
                .filter(i -> i == OCCUPIED_SEAT)
                .count();
    }
}
