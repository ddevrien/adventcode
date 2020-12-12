package be.devriendt.advent.s2020;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Day9EncodingError {

    public static long findContiguousSetForInvalidNumber(int windowSize, List<Long> numbers) {
        long invalidNumber = findInvalidNumber(windowSize, numbers);

        for (int i = 0; i < numbers.size(); i++) {
            int j = i+1;
            long sum = numbers.get(i);
            long min = sum;
            long max = sum;
            while (sum < invalidNumber) {
                Long next = numbers.get(j++);
                sum += next;
                min = Math.min(min, next);
                max = Math.max(max, next);

                if (sum == invalidNumber) {
                    return min + max;
                }
            }
        }

        return -1;
    }

    public static long findInvalidNumber(int windowSize, List<Long> numbers) {
        HashSet<Long> window = new HashSet<>(windowSize);
        final AtomicInteger cursor = new AtomicInteger(0);
        window.addAll(numbers.subList(0, windowSize));

        Optional<Long> solution = numbers.stream()
                .skip(windowSize)
                .filter(number -> {
                    Optional<Long> result = window.stream()
                            .filter(elem -> window.contains(number - elem))
                            .findAny();
                    window.remove(numbers.get(cursor.get()));
                    window.add(number);
                    cursor.incrementAndGet();
                    return result.isEmpty();
                })
                .findFirst();

        return solution.orElse(-1L);
    }
}
