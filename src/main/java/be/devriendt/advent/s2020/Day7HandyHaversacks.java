package be.devriendt.advent.s2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day7HandyHaversacks {

    private static class Bag {
        public final String color;
        public final List<BagEntry> contents = new ArrayList<>();

        public Bag(String color) {
            this.color = color;
        }

        public boolean canContain(String targetColor) {
            for (BagEntry entry: contents) {
                if (entry.bag.color.equals(targetColor) || entry.bag.canContain(targetColor)) {
                    return true;
                }
            }
            return false;
        }

        public int getTotalBagsCount() {
            int sum = 0;
            for (BagEntry entry: contents) {
                sum += entry.count + entry.count * entry.bag.getTotalBagsCount();
            }
            return sum;
        }
    }

    private static class BagEntry {
        public final Bag bag;
        public final int count;

        public BagEntry(Bag bag, int count) {
            this.bag = bag;
            this.count = count;
        }
    }

    public static int getTotalValidCombinations(String targetColor, List<String> rules) {
        HashMap<String, Bag> bags = new HashMap<>();
        rules.stream().forEach(rule -> toBagRule(rule, bags));
        return (int) bags.values().stream()
                .filter(bag -> bag.canContain(targetColor))
                .count();
    }

    public static int getTotalCost(String targetColor, List<String> rules) {
        HashMap<String, Bag> bags = new HashMap<>();
        rules.stream().forEach(rule -> toBagRule(rule, bags));
        return bags.get(targetColor).getTotalBagsCount();
    }

    private static void toBagRule(String rule, HashMap<String, Bag> bags) {
        String[] parts = rule.split(" bags contain ");
        String[] contents = parts[1].split(" bags?[,.] ?");
        Bag bagRule = bags.computeIfAbsent(parts[0], Bag::new);
        if (!contents[0].startsWith("no other")) {
            for(String entry: contents) {
                String bagEntryColor = entry.substring(2);
                Bag bag = bags.computeIfAbsent(bagEntryColor, Bag::new);
                bagRule.contents.add(new BagEntry(bag, Integer.parseInt(entry.substring(0, 1))));
            }
        }
    }
}
