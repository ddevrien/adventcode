package be.devriendt.advent.s2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.arraycopy;
import static java.lang.System.in;

public class Day14DockingData {

    public static long getSumOfMemoryValues(List<String> input) {
        String curBitmask = null;
        Map<Integer, Long> memory = new HashMap<>();

        for(String instr: input) {
            String[] parts = instr.split(" = ");
            if (parts[0].startsWith("mask")) {
                curBitmask = parts[1];
            } else {
                Integer memAddress = Integer.parseInt(parts[0].substring(4, parts[0].length()-1));
                Long memValue = Long.parseLong(parts[1]);
                memory.put(memAddress, applyMask(memValue, curBitmask));
            }
        }

        return memory.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    private static long applyMask(Long number, String bitmaskStr) {
        char[] input = Long.toBinaryString(number).toCharArray();
        char[] bitmask = bitmaskStr.toCharArray();
        char[] output = new char[bitmask.length];

        for (int i = 0; i < bitmask.length; i++) {
            if (i < bitmask.length - input.length) {
                if (bitmask[i] == 'X') {
                    output[i] = '0';
                } else {
                    output[i] = bitmask[i];
                }
            } else {
                if (bitmask[i] == 'X') {
                    output[i] = input[input.length - (bitmask.length - i)];
                } else {
                    output[i] = bitmask[i];
                }
            }
        }

        return Long.parseLong(new String(output), 2);
    }

    public static long getSumOfMemoryValuesPart2(List<String> input) {
        String curBitmask = null;
        Map<Long, Long> memory = new HashMap<>();

        for(String instr: input) {
            String[] parts = instr.split(" = ");
            if (parts[0].startsWith("mask")) {
                curBitmask = parts[1];
            } else {
                Integer memAddress = Integer.parseInt(parts[0].substring(4, parts[0].length()-1));
                Long memValue = Long.parseLong(parts[1]);
                String maskedMemAddress =  applyMaskToAddress(memAddress, curBitmask);
                writeToMemAddresses(memory, maskedMemAddress.toCharArray(), memValue, 0);
            }
        }

        return memory.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    private static String applyMaskToAddress(Integer address, String bitmaskStr) {
        char[] input = Integer.toBinaryString(address).toCharArray();
        char[] bitmask = bitmaskStr.toCharArray();
        char[] output = new char[bitmask.length];

        for (int i = 0; i < bitmask.length; i++) {
            if (i < bitmask.length - input.length) {
                if (bitmask[i] == '1') {
                    output[i] = '1';
                } else {
                    output[i] = bitmask[i];
                }
            } else {
                if (bitmask[i] == '0') {
                    output[i] = input[input.length - (bitmask.length - i)];
                } else {
                    output[i] = bitmask[i];
                }
            }
        }

        return new String(output);
    }

    private static void writeToMemAddresses(Map<Long, Long> memory, char[] maskedMemAddress, Long value, int index) {
        while (index < maskedMemAddress.length && maskedMemAddress[index] != 'X') {
            index++;
        }

        if (index == maskedMemAddress.length) {
            memory.put(Long.parseLong(new String(maskedMemAddress), 2), value);
        } else {
            char[] with0 = new char[maskedMemAddress.length];
            char[] with1 = new char[maskedMemAddress.length];
            arraycopy(maskedMemAddress, 0, with0, 0, with0.length);
            arraycopy(maskedMemAddress, 0, with1, 0, with0.length);

            with0[index] = '0';
            with1[index] = '1';

            writeToMemAddresses(memory, with0, value, index+1);
            writeToMemAddresses(memory, with1, value, index+1);
        }
    }
}
