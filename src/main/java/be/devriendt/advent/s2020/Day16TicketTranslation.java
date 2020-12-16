package be.devriendt.advent.s2020;

import java.util.*;
import java.util.stream.Collectors;

public class Day16TicketTranslation {

    public static int findTicketScanningErrorRate(List<String> input) {
        Tickets tickets = new Tickets(input);

        return tickets.invalidNumbers.stream().reduce(0, Integer::sum);
    }

    public static long findTicketSpecifics(List<String> input) {
        long result = 1;
        Tickets tickets = new Tickets(input);

        List<List<TicketRule>> ticketRulesOnPos = new ArrayList<>();
        for (int i = 0; i < tickets.ticketRules.size(); i++) {
            ArrayList<TicketRule> conformingRules = new ArrayList<>();
            ticketRulesOnPos.add(conformingRules);
            for (TicketRule rule: tickets.ticketRules) {
                if (ruleConformsWithAllTicketsOnPos(rule, i, tickets.validTickets)) {
                    conformingRules.add(rule);
                }
            }
        }

        List<List<TicketRule>> sortedBySize = new ArrayList<>(ticketRulesOnPos);
        sortedBySize.sort(Comparator.comparing(List::size));

        for (int pos = 0; pos < sortedBySize.size(); pos++) {
            TicketRule ruleOnPos = sortedBySize.get(pos).get(0);
            if (ruleOnPos.name.startsWith("departure")) {
                result *= tickets.yourTicket.get(getPos(ticketRulesOnPos));
            }
            ticketRulesOnPos.forEach(list -> list.remove(ruleOnPos));
        }

        return result;
    }

    private static int getPos(List<List<TicketRule>> ticketRules) {
        for (int pos = 0; pos < ticketRules.size(); pos++) {
            List<TicketRule> ticketRulesOnPos = ticketRules.get(pos);
            if (ticketRulesOnPos.size() == 1) {
                return pos;
            }
        }
        return -1;
    }

    private static boolean ruleConformsWithAllTicketsOnPos(TicketRule rule, final int pos, List<List<Integer>> validTickets) {
        return validTickets.stream()
                .map(ticket -> ticket.get(pos))
                .noneMatch(rule::valueViolatesRule);
    }

    private static class Tickets {
        Set<Integer> validNumbers = new HashSet<>();
        List<Integer> invalidNumbers = new ArrayList<>();
        List<TicketRule> ticketRules = new ArrayList<>();
        List<List<Integer>> validTickets = new ArrayList<>();
        List<Integer> yourTicket = null;

        private Tickets(List<String> input) {
            boolean scanningRules = true;
            boolean scanningYourTicket = false;
            boolean scanningOtherTickets = false;

            for (String line: input) {
                if (scanningRules) {
                    if (line.isBlank()) {
                        scanningRules = false;
                    } else {
                        TicketRule rule = TicketRule.toTicketRule(line);
                        addValidNumbers(rule);
                        ticketRules.add(rule);
                    }
                }

                if (scanningYourTicket) {
                    yourTicket = ticketAsNumbers(line);
                    scanningYourTicket = false;
                } else if (line.equals("your ticket:")) {
                    scanningYourTicket = true;
                }

                if (scanningOtherTickets) {
                    List<Integer> ticketAsNumbers = ticketAsNumbers(line);
                    ticketAsNumbers.stream()
                            .filter(num -> !validNumbers.contains(num))
                            .forEach(invalidNumbers::add);
                    if (validNumbers.containsAll(ticketAsNumbers)) {
                        validTickets.add(ticketAsNumbers);
                    }
                } else if (line.equals("nearby tickets:")) {
                    scanningOtherTickets = true;
                }
            }
        }

        private void addValidNumbers(TicketRule rule) {
            for (int i = rule.from; i <= rule.to; i++) {
                validNumbers.add(i);
            }
            for (int i = rule.from2; i <= rule.to2; i++) {
                validNumbers.add(i);
            }
        }

        private List<Integer> ticketAsNumbers(String line) {
            return Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    private static class TicketRule {
        final String name;
        final int from;
        final int to;
        final int from2;
        final int to2;

        private TicketRule(String name, int from, int to, int from2, int to2) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.from2 = from2;
            this.to2 = to2;
        }

        static TicketRule toTicketRule(String line) {
            String[] parts = line.split("(: | or |-)");
            return new TicketRule(parts[0],
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4]));
        }

        boolean valueViolatesRule(int num) {
            return (num < from || num > to) && (num < from2 || num > to2);
        }

    }

}
