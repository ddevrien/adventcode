package be.devriendt.advent.s2022;

import java.util.List;

import static be.devriendt.advent.s2022.Day14RegolithReservoir.BlockType.AIR;
import static be.devriendt.advent.s2022.Day14RegolithReservoir.BlockType.SAND;

public class Day14RegolithReservoir {

    private static final int CAVE_SIZE_X = 1000;
    private static final int CAVE_SIZE_Y = 200;
    private static final int SAND_SOURCE_X = 500;
    private static final int SAND_SOURCE_Y = 0;

    public static int findTotalSandUnits(List<String> input, boolean infinityBottom) {
        Cave cave = new Cave(infinityBottom);
        cave.fillInRocks(input);

        int sandUnits = cave.runSandSimulation();
//        cave.print();

        return sandUnits;
    }

    private static class Cave {
        Block[][] blocks;

        int leftX = Integer.MAX_VALUE;
        int rightX;
        int topY = 0; // sand source
        int bottomY;

        boolean infinityBottom;

        public Cave(boolean infinityBottom) {
            this.blocks = new Block[CAVE_SIZE_Y][CAVE_SIZE_X];
            for (int y = 0; y < blocks.length; y++) {
                for (int x = 0; x < blocks[y].length; x++) {
                    blocks[y][x] = new Block(BlockType.AIR);
                }
            }
            blocks[SAND_SOURCE_Y][SAND_SOURCE_X].type = BlockType.SAND_SOURCE;
            this.infinityBottom = infinityBottom;
        }

        public void fillInRocks(List<String> input) {
            input.stream()
                    .map(line -> line.split(" -> "))
                    .forEach(points -> fillInRocks(points));
        }

        public int runSandSimulation() {
            int sandUnits = 0;
            while(addSandUnit()) {
                sandUnits++;
            }
            return infinityBottom ? sandUnits : sandUnits + 1;
        }

        public boolean addSandUnit() {
            int movingSandX = SAND_SOURCE_X;
            int movingSandY = SAND_SOURCE_Y;
            boolean moving = true;

            while (moving) {
                while (movingSandY + 1 < bottomY + 2 && blocks[movingSandY+1][movingSandX].type == AIR) {
                    movingSandY++;
                }

                if (movingSandY + 1 == bottomY + 2) {
                    if (infinityBottom) {
                        return false;
                    } else {
                        blocks[movingSandY][movingSandX].type = SAND;
                        return true;
                    }
                }

                if (blocks[movingSandY+1][movingSandX-1].type == AIR) {
                    movingSandY++;
                    movingSandX--;
                    leftX = Math.min(leftX, movingSandX);
                } else if (blocks[movingSandY+1][movingSandX+1].type == AIR) {
                    movingSandY++;
                    movingSandX++;
                    rightX = Math.max(rightX, movingSandX);
                } else {
                    moving = false;
                }
            }

            blocks[movingSandY][movingSandX].type = SAND;
            return movingSandX == SAND_SOURCE_X && movingSandY == SAND_SOURCE_Y ? false : true;
        }

        public void print() {
            for (int y = topY; y <= bottomY + 2; y++) {
                for (int x = leftX - 2; x <= rightX + 2; x++) {
                    System.out.print(blocks[y][x]);
                }
                System.out.println();
            }
        }

        private void fillInRocks(String[] points) {
            String[] coordinates = points[0].split(",");
            int startX = Integer.parseInt(coordinates[0]);
            int startY = Integer.parseInt(coordinates[1]);

            for (int i = 1; i < points.length; i++) {
                coordinates = points[i].split(",");
                int endX = Integer.parseInt(coordinates[0]);
                int endY = Integer.parseInt(coordinates[1]);

                int minX = Math.min(startX, endX);
                int maxX = Math.max(startX, endX);
                int minY = Math.min(startY, endY);
                int maxY = Math.max(startY, endY);

                if (startX == endX) {
                    for (int y = minY; y <= maxY; y++) {
                        blocks[y][startX].type = BlockType.ROCK;
                    }
                } else {
                    for (int x = minX; x <= maxX; x++) {
                        blocks[startY][x].type = BlockType.ROCK;
                    }
                }

                startX = endX;
                startY = endY;

                leftX = Math.min(leftX, minX);
                rightX = Math.max(rightX, maxX);
                bottomY = Math.max(bottomY, maxY);
            }
        }
    }


    private static class Block {
        BlockType type;

        public Block(BlockType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return switch(type) {
                case ROCK -> '#';
                case AIR -> '.';
                case SAND -> 'o';
                case SAND_SOURCE -> '+';
            } + "";
        }
    }

    enum BlockType {
        ROCK, AIR, SAND, SAND_SOURCE
    }
}
