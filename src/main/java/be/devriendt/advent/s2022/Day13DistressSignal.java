package be.devriendt.advent.s2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class Day13DistressSignal {

    private static final List<String> DIVIDERS = List.of("[[2]]", "[[6]]");

    public static int findSumOfPacketPairsIndicesInRightOrder(List<String> input) {
        int indicesSum = 0;

        for (int i = 0; i < input.size(); i += 3) {
            Packet left = Packet.fromLine(input.get(i));
            Packet right = Packet.fromLine(input.get(i+1));
            if (left.isInRightOrder(right)) {
                indicesSum += i / 3 + 1;
            }
        }

        return indicesSum;
    }

    public static int sortPacketsInRightOrder(List<String> input) {
        List<Packet> packets = new ArrayList<>();
        List<Packet> dividers = DIVIDERS.stream().map(Packet::fromLine).collect(Collectors.toList());
        int indicesSum = 1;

        for (int i = 0; i < input.size(); i += 3) {
            packets.add(Packet.fromLine(input.get(i)));
            packets.add(Packet.fromLine(input.get(i+1)));
        }
        packets.addAll(dividers);
        Collections.sort(packets);

        for (int i = 0; i < packets.size(); i++) {
            if (dividers.contains(packets.get(i))) {
                indicesSum *= i + 1;
            }
        }

        return indicesSum;
    }

    private static class Packet implements Comparable<Packet> {

        Integer element;
        List<Packet> elements;

        public Packet(Integer element) {
            this.element = element;
        }

        public Packet(List<Packet> elements) {
            this.elements = elements;
        }

        static Packet fromLine(String line) {
            if (line.equals("[]")) {
                return new Packet(emptyList());
            }
            try {
                return new Packet(Integer.valueOf(line));
            } catch (NumberFormatException e) {

            }

            char[] chars = line.toCharArray();
            int nestedLists = 0;
            int beginIndex = 1;
            List<String> parts = new ArrayList<>();

            for (int i = beginIndex; i < chars.length - 1; i++) {
                switch (chars[i]) {
                    case '[' -> nestedLists++;
                    case ']' -> nestedLists--;
                    case ',' -> {
                        if (nestedLists == 0) {
                            parts.add(line.substring(beginIndex, i));
                            beginIndex = i + 1;
                        }
                    }
                }
            }
            parts.add(line.substring(beginIndex, chars.length - 1));

            List<Packet> packets = parts.stream().map(Packet::fromLine)
                    .collect(Collectors.toList());
            return new Packet(packets);
        }

        public boolean isInteger() {
            return this.element != null;
        }

        public boolean isList() {
            return this.elements != null;
        }

        public boolean isEmpty() {
            return !isInteger() && elements.isEmpty();
        }

        public boolean isInRightOrder(Packet right) {
            return this.compareTo(right) < 0;
        }

        @Override
        public int compareTo(Packet right) {
            if (this.isInteger() && right.isInteger()) {
                return this.element.compareTo(right.element);
            } else if (this.isList() && right.isList()){
                for (int i = 0; i < this.elements.size(); i++) {
                    if (i >= right.elements.size()) {
                        return 1;
                    }
                    int compare = this.elements.get(i).compareTo(right.elements.get(i));
                    if (compare < 0) {
                        return -1;
                    } else if (compare > 0) {
                        return 1;
                    }
                }
                return this.elements.size() < right.elements.size() ? -1 : 0;
            } else if (this.isInteger()) {
                return new Packet(singletonList(this)).compareTo(right);
            } else {
                return this.compareTo(new Packet(singletonList(right)));
            }
        }

        @Override
        public String toString() {
            return (isInteger() ? this.element : this.elements).toString();
        }
    }
}
