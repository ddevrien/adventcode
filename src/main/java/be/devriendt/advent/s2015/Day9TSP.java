package be.devriendt.advent.s2015;

import java.util.*;

/**
 * Created by Dennis on 24/01/2016.
 *
 * 2022: we revisit!
 */
public class Day9TSP {

    public static int findShortestRoute(List<String> input) {
        HashMap<String, Node> nodes = createNodes(input);

        return nodes.values().stream()
                .mapToInt(node -> node.findShortestRoute(nodes.size()))
                .min()
                .orElseThrow(NoSuchElementException::new);
    }

    public static int findLongestRoute(List<String> input) {
        HashMap<String, Node> nodes = createNodes(input);

        return nodes.values().stream()
                .mapToInt(node -> node.findLongestRoute(nodes.size()))
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    private static HashMap<String, Node> createNodes(List<String> input) {
        HashMap<String, Node> nodes = new HashMap<>();

        for(String line: input) {
            String[] parts = line.split(" ");
            Node from = nodes.computeIfAbsent(parts[0], name -> new Node(name));
            Node to = nodes.computeIfAbsent(parts[2], name -> new Node(name));
            int distance = Integer.parseInt(parts[4]);
            from.addRoute(new Route(from, to, distance));
            to.addRoute(new Route(to, from, distance));
        }
        return nodes;
    }

    private static class Node {
        String name;
        List<Route> routes;

        public Node(String name) {
            this.name = name;
            this.routes = new ArrayList<>();
        }

        public void addRoute(Route route) {
            routes.add(route);
        }

        public int findShortestRoute(int nodesToVisit) {
            return routes.stream()
                    .mapToInt(r -> r.to.findShortestRoute(r.distance, new ArrayList<>(List.of(r.from)), nodesToVisit))
                    .min()
                    .orElseThrow(NoSuchElementException::new);
        }

        private int findShortestRoute(int curWeight, List<Node> visited, int nodesToVisit) {
            if (visited.size() == nodesToVisit - 1) {
                return curWeight;
            }

            int min = Integer.MAX_VALUE;
            for(Route r: routes) {
                if (!visited.contains(r.to)) {
                    visited.add(r.from);
                    min = Math.min(min, r.to.findShortestRoute(curWeight + r.distance, visited, nodesToVisit));
                    visited.remove(r.from);
                }
            }
            return min;
        }

        public int findLongestRoute(int nodesToVisit) {
            return routes.stream()
                    .mapToInt(r -> r.to.findLongestRoute(r.distance, new ArrayList<>(List.of(r.from)), nodesToVisit))
                    .max()
                    .orElseThrow(NoSuchElementException::new);
        }

        private int findLongestRoute(int curWeight, List<Node> visited, int nodesToVisit) {
            if (visited.size() == nodesToVisit - 1) {
                return curWeight;
            }

            int max = 0;
            for(Route r: routes) {
                if (!visited.contains(r.to)) {
                    visited.add(r.from);
                    max = Math.max(max, r.to.findLongestRoute(curWeight + r.distance, visited, nodesToVisit));
                    visited.remove(r.from);
                }
            }
            return max;
        }
    }

    private static class Route {
        Node from;
        Node to;
        int distance;

        public Route(Node from, Node to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return from.name + " -> " + to.name + ": " + distance;
        }
    }

}
