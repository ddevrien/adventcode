package be.devriendt.advent.s2015;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dennis on 24/01/2016.
 */
public class Day9TSP {

    private static final Pattern PATTERN = Pattern.compile("(\\w+) to (\\w+) = (\\d+)$");

    public static int getShortestRoute(String resourcePath) throws URISyntaxException, IOException {
        return shortestRoute(
                Files.readAllLines(Paths.get(Day9TSP.class.getResource(resourcePath).toURI())));
    }


    public static int shortestRoute(List<String> routeWeights) {
        Map<String, Node> nodes = toNodeMap(routeWeights);
        List <Route> routes = new ArrayList<>();

        getAllRoutesFromNodes(routes, nodes.values());
        return 0;
    }

    private static void getAllRoutesFromNodes(List<Route> routes, Collection<Node> nodes) {
        if (nodes.size() == 2) {
//           nodes.
        }
    }

    private static Map<String, Node> toNodeMap(List<String> routeWeights) {
        Map<String, Node> nodes = new HashMap<>();

        for (String routeWeight: routeWeights) {
            Matcher m = PATTERN.matcher(routeWeight);
            m.find();

            Node from = getOrCreate(m.group(1), nodes);
            Node to = getOrCreate(m.group(2), nodes);
            int weight = Integer.parseInt(m.group(3));

            from.addRoute(new Route(from, to, weight));
            to.addRoute(new Route(to, from, weight));
        }

        return nodes;
    }

    private static Node getOrCreate(String cityName, Map<String, Node> nodes) {
        Node node = nodes.get(cityName);
        if (node == null) {
            node = new Node(cityName);
            nodes.put(cityName, node);
        }
        return node;
    }

    private static class Route {
        private Node from;
        private Node to;
        private int weight;

        public Route(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Node {
        private String name;
        private List<Route> routes;

        public Node(String name) {
            this.name = name;
            this.routes = new ArrayList<>();
        }

        public void addRoute(Route route) {
            routes.add(route);
        }
    }
}
