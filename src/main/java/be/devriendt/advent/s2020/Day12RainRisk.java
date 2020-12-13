package be.devriendt.advent.s2020;

import java.util.List;

import static java.util.Arrays.asList;

public class Day12RainRisk {

    private static final List<Action> WIND_DIRECTIONS = asList(Action.N, Action.E, Action.S, Action.W);

    private enum Action {
        N, E, S, W, L, R, F
    }

    private static class Instruction {
        Action action;
        int units;

        Instruction(Action action, int units) {
            this.action = action;
            this.units = units;
        }

        static Instruction fromLine(String line) {
            return new Instruction(Action.valueOf(line.substring(0, 1)), Integer.parseInt(line.substring(1)));
        }
    }

    private static class Ship {
        private int x = 0;
        private int y = 0;
        private int waypointX = 10;
        private int waypointY = 1;

        private Action facing = Action.E;

        void move(Instruction instruction) {
            Action action = instruction.action != Action.F ? instruction.action : facing;

            switch (action) {
                case E: x += instruction.units; break;
                case W: x -= instruction.units; break;
                case N: y += instruction.units; break;
                case S: y -= instruction.units; break;
                case L: case R: rotate(instruction); break;
            }
        }

        void moveWithWaypoint(Instruction instruction) {
            switch (instruction.action) {
                case E: waypointX += instruction.units; break;
                case W: waypointX -= instruction.units; break;
                case N: waypointY += instruction.units; break;
                case S: waypointY -= instruction.units; break;
                case L: case R: waypointRotate(instruction); break;
                case F: waypointForward(instruction.units); break;
            }
        }

        int getManhattanDistance() {
            return Math.abs(x) + Math.abs(y);
        }

        private void rotate(Instruction instruction) {
            int indexOf = WIND_DIRECTIONS.indexOf(facing);
            int target;
            if (instruction.action == Action.L) {
                target = (indexOf - (instruction.units / 90)) % 4;
            } else if (instruction.action == Action.R) {
                target = (indexOf + (instruction.units / 90)) % 4;
            } else {
                throw new IllegalArgumentException("L or R");
            }
            if (target < 0) {
                target += 4;
            }
            facing = WIND_DIRECTIONS.get(target);
        }

        private void waypointForward(int times) {
            x += times * waypointX;
            y += times * waypointY;
        }

        private void waypointRotate(Instruction instruction) {
            double rads = (instruction.units / 90) * (Math.PI / 2d);
            if (instruction.action == Action.R) {
                rads *= -1;
            }
            int newWayX = (int)Math.round(waypointX * Math.cos(rads) - waypointY * Math.sin(rads));
            int newWayY = (int)Math.round(waypointX * Math.sin(rads) + waypointY * Math.cos(rads));
            waypointX = newWayX;
            waypointY = newWayY;
        }
    }

    public static int getManhattanDistance(List<String> input) {
        Ship ship = new Ship();

        input.stream()
                .map(Instruction::fromLine)
                .forEach(ship::move);

        return ship.getManhattanDistance();
    }

    public static int getManhattanDistanceWithWaypoint(List<String> input) {
        Ship ship = new Ship();

        input.stream()
                .map(Instruction::fromLine)
                .forEach(ship::moveWithWaypoint);

        return ship.getManhattanDistance();
    }

}
