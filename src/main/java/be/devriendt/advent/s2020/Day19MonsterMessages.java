package be.devriendt.advent.s2020;

import java.util.*;

public class Day19MonsterMessages {

//    private static class Rule {
//        private final int nr;
//        private final String rule;
//        private final List<String> ruleMatchers = new ArrayList<>();
//        private boolean finalized;
//
//        public Rule(int nr, String rule) {
//            this.nr = nr;
//            this.rule = rule;
//            if (rule.charAt(0) == '"') {
//                ruleMatchers.add(this.rule);
//                this.finalized = true;
//            }
//        }
//
//        public boolean dependsOnRule(int ruleNumber) {
//            return !finalized && rule.indexOf((ruleNumber+'0')) != -1;
//        }
//
//        public boolean fillInRule(Rule r) {
//            if (rule.indexOf('|') == -1) {
//                rule = rule.replace(r.nr+"", r.rule);
//            }
//            if (!rule.matches(".*\\d.*")) {
//                finalized = true;
//                return true;
//            }
//            return false;
//        }
//
//        public boolean matchesRule(String input) {
//            return false;
//        }
//    }

//    public static int getNumberOfMessagesMatchingRules(List<String> input) {
//        Map<Integer, Rule> rules = new HashMap<>();
//        List<Rule> newlyFinalizedRules = new ArrayList<>();
//        int i = 0;
//        String readLine = input.get(i);
//        while (!readLine.isEmpty()) {
//            String[] parts = readLine.split(": ");
//            int ruleNr = Integer.parseInt(parts[0]);
//            Rule rule = new Rule(ruleNr, parts[1]);
//            rules.put(ruleNr, rule);
//
//            if (rule.finalized) {
//                newlyFinalizedRules.add(rule);
//            }
//            readLine = input.get(++i);
//        }
//
//        while (!newlyFinalizedRules.isEmpty()) {
//            for (Rule rule: new ArrayList<>(newlyFinalizedRules)) {
//                rules.values().stream()
//                        .filter(r -> r.dependsOnRule(rule.nr))
//                        .forEach(r -> {
//                            boolean finalized = r.fillInRule(rule);
//                            if (finalized) {
//                                newlyFinalizedRules.add(r);
//                            }
//                        });
//                newlyFinalizedRules.remove(rule);
//            }
//        }
//
//        return -1;
//    }

    private static class Rule {
        private final int nr;
        private final String rule;

        public Rule(int nr, String rule) {
            this.nr = nr;
            this.rule = rule;
        }

//        public boolean matches(String input, )
    }

    public static int getNumberOfMessagesMatchingRules(List<String> input) {
        return -1;
    }


}
