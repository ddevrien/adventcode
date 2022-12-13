package be.devriendt.advent.s2022;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

public class Day9RopeBridge {

    public static int findNumberOfVisitedLocations(int nrOfKnots, List<String> input) {
        Rope rope = new Rope(nrOfKnots);
        input.forEach(rope::move);
        return rope.visitedLocations.size();
    }

    private static class Rope {

        Set<Point> visitedLocations;
        List<Point> knots;

        public Rope(int nrOfKnots) {
            this.visitedLocations = new HashSet<>();
            this.visitedLocations.add(new Point(0, 0));

            this.knots = new ArrayList<>();
            for (int i = 0; i < nrOfKnots; i++) {
                this.knots.add(new Point(0, 0));
            }
        }

        public void move(String instruction) {
            String[] parts = instruction.split(" ");

            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                switch (parts[0]) {
                    case "U" -> knots.get(0).translate(0, 1);
                    case "D" -> knots.get(0).translate(0, -1);
                    case "L" -> knots.get(0).translate(-1, 0);
                    case "R" -> knots.get(0).translate(1, 0);
                }

                for (int knot = 1; knot < knots.size(); knot++) {
                    Point prevKnot = knots.get(knot - 1);
                    Point curKnot = knots.get(knot);
                    int xDiff = abs(prevKnot.x - curKnot.x);
                    int yDiff = abs(prevKnot.y - curKnot.y);

                    if (xDiff + yDiff > 2) {
                        curKnot.translate(prevKnot.x - curKnot.x > 0 ? 1 : -1, prevKnot.y - curKnot.y > 0 ? 1 : -1);
                    } else if (xDiff > 1) {
                        curKnot.translate(prevKnot.x - curKnot.x > 0 ? 1 : -1, 0);
                    } else if (yDiff > 1) {
                        curKnot.translate(0, prevKnot.y - curKnot.y > 0 ? 1 : -1);
                    }

                    if (knot == knots.size() - 1 && (xDiff > 1 || yDiff > 1)) {
                        visitedLocations.add(curKnot.getLocation());
                    }
                }
            }
        }
    }
}
