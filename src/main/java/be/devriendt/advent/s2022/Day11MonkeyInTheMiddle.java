package be.devriendt.advent.s2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class Day11MonkeyInTheMiddle {

    private static final int MONKEYS_TO_CONSIDER = 2;

    public static long findMonkeyBusiness(List<String> input, int rounds, int worryLevelDividor) {
        List<Monkey> monkeys = setupMonkeys(input, worryLevelDividor);

        for (int i = 0; i < rounds; i++) {
            monkeys.forEach(Monkey::doTurn);
        }

        return monkeys.stream()
                .sorted(Comparator.comparing(Monkey::getItemsInspected, reverseOrder()))
                .limit(MONKEYS_TO_CONSIDER)
                .mapToLong(Monkey::getItemsInspected)
                .reduce(1, (a, b) -> a * b);
    }

    private static List<Monkey> setupMonkeys(List<String> input, int worryLevelDividor) {
        List<Monkey> monkeys = new ArrayList<>();
        int commonWorryLevelDividor = 1;

        for (int i = 0; i < input.size(); i += 7) {
            List<Item> startingItems = Arrays.stream(input.get(i + 1).replaceAll(".*: ", "").split(", "))
                    .map(Integer::valueOf)
                    .map(Item::new)
                    .collect(Collectors.toList());
            LongUnaryOperator operator = parseOperation(input.get(i + 2));
            int throwTestDividor = Integer.parseInt(input.get(i + 3).replaceAll("\s*Test: divisible by ", ""));
            commonWorryLevelDividor *= throwTestDividor;
            monkeys.add(new Monkey(i / 7, startingItems, operator, throwTestDividor, worryLevelDividor));
        }

        for (int i = 0; i < input.size(); i += 7) {
            int ifTrueTarget = Integer.parseInt(input.get(i + 4).replaceAll("\s*If true: throw to monkey ", ""));
            int ifFalseTarget = Integer.parseInt(input.get(i + 5).replaceAll("\s*If false: throw to monkey ", ""));
            Monkey monkey = monkeys.get(i / 7);
            monkey.firstTarget = monkeys.get(ifTrueTarget);
            monkey.secondTarget = monkeys.get(ifFalseTarget);
            monkey.commonWorryLevelDividor = commonWorryLevelDividor;
        }

        return monkeys;
    }

    private static LongUnaryOperator parseOperation(String s) {
        String[] parts = s.replaceAll("\s*Operation: new = ", "").split(" ");
        if (parts[2].equals("old")) {
            return switch (parts[1]) {
                case "+" -> a -> a + a;
                case "*" -> a -> a * a;
                default -> throw new IllegalArgumentException("Unknown operation");
            };
        } else {
            return switch (parts[1]) {
                case "+" -> a -> a + Integer.parseInt(parts[2]);
                case "*" -> a -> a * Integer.parseInt(parts[2]);
                default -> throw new IllegalArgumentException("Unknown operation");
            };
        }
    }

    private static class Monkey {
        private int idx;
        private List<Item> items;
        private LongUnaryOperator operator;
        private long throwTestDividor;
        private long worryLevelDividor;
        private Integer commonWorryLevelDividor;
        private Monkey firstTarget;
        private Monkey secondTarget;

        private int itemsInspected;

        public Monkey(int idx, List<Item> items, LongUnaryOperator operator, int throwTestDividor, int worryLevelDividor) {
            this.idx = idx;
            this.items = items;
            this.operator = operator;
            this.throwTestDividor = throwTestDividor;
            this.worryLevelDividor = worryLevelDividor;
            this.itemsInspected = 0;
        }

        public void doTurn() {
            items.stream().forEach(item -> {
                long newWorryLevel = operator.applyAsLong(item.worryLevel) / worryLevelDividor;
                item.worryLevel = newWorryLevel % commonWorryLevelDividor;

                if (item.worryLevel % throwTestDividor == 0) {
                    firstTarget.items.add(item);
                } else {
                    secondTarget.items.add(item);
                }

                itemsInspected++;
            });
            items.clear();
        }

        public int getItemsInspected() {
            return itemsInspected;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "idx=" + idx +
                    ", items=" + items +
                    ", throwTestDividor=" + throwTestDividor +
                    ", firstTarget=" + (firstTarget != null ? firstTarget.idx : null) +
                    ", secondTarget=" + (secondTarget != null ? secondTarget.idx : null) +
                    ", itemsInspected=" + itemsInspected +
                    '}';
        }
    }

    private static class Item {
        private long worryLevel;

        public Item(long worryLevel) {
            this.worryLevel = worryLevel;
        }

        @Override
        public String toString() {
            return "Item{" + worryLevel + '}';
        }
    }
}