package be.devriendt.advent.s2015;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dennis on 14/12/2015.
 */
public class Day7Computer {

    private static final String ASSIGMENT = " -> ";
    private static final String AND = "AND";
    private static final String OR = "OR";
    private static final String LSHIFT = "LSHIFT";
    private static final String RSHIFT = "RSHIFT";
    private static final String NOT = "NOT";

    private static final Pattern PATTERN = Pattern.compile("(\\w+) ?(\\w+)? ?(\\w+)?$");

    public static int getWireSignalFromInstructions(String wireId, String resourcePath) throws URISyntaxException, IOException {
        return instructionsToWireSignal(wireId,
                Files.readAllLines(Paths.get(Day7Computer.class.getResource(resourcePath).toURI())));
    }

    public static int instructionsToWireSignal(String wireId, List<String> instructions) {
        Map<String, String> assignmentMap = toAssignmentMap(instructions);
        return eval(wireId, assignmentMap);
    }

    private static int eval(String wireId, Map<String, String> assignmentMap) {
        if (isNumeric(wireId)) { // numeric assignment
            return Integer.parseInt(wireId);
        }

        int signal;
        String expression = assignmentMap.get(wireId);
        Matcher m = PATTERN.matcher(expression);
        m.find();

        if (m.group(2) == null) { // wire assignment
            signal = eval(m.group(1), assignmentMap);
        } else if (m.group(2).equals(AND)) {
            signal = eval(m.group(1), assignmentMap) & eval(m.group(3), assignmentMap);
        } else if (m.group(2).equals(OR)) {
            signal = eval(m.group(1), assignmentMap) | eval(m.group(3), assignmentMap);
        } else if (m.group(2).equals(LSHIFT)) {
            signal = eval(m.group(1), assignmentMap) << Integer.parseInt(m.group(3));
        } else if (m.group(2).equals(RSHIFT)) {
            signal = eval(m.group(1), assignmentMap) >> Integer.parseInt(m.group(3));
        } else if (m.group(1).equals(NOT)) {
            signal = ~eval(m.group(2), assignmentMap) & 0xffff;
        } else {
            throw new IllegalArgumentException("Dunno");
        }

        assignmentMap.put(wireId, String.valueOf(signal));
        return signal;
    }

    private static Map<String,String> toAssignmentMap(List<String> instructions) {
        Map<String, String> assignmentMap = new HashMap<>();
        for (String instruction: instructions) {
            String[] parts = instruction.split(ASSIGMENT);
            assignmentMap.put(parts[1], parts[0]);
        }
        return assignmentMap;
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}
