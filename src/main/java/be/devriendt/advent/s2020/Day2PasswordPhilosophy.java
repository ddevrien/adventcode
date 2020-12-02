package be.devriendt.advent.s2020;

import java.util.List;

public class Day2PasswordPhilosophy {

    private static class PasswordPolicy {
        final char letter;
        final int param1;
        final int param2;
        final String password;

        public PasswordPolicy(char letter, int param1, int param2, String password) {
            this.letter = letter;
            this.param1 = param1;
            this.param2 = param2;
            this.password = password;
        }

        public boolean isValid() {
            long count = password.chars().filter(c -> c == letter).count();
            return count >= param1 && count <= param2;
        }

        public boolean isValid2() {
            boolean pos1Valid = password.charAt(param1 - 1) == letter;
            boolean pos2Valid = password.charAt(param2 - 1) == letter;
            return pos1Valid ^ pos2Valid;
        }
    }

    public static int numberOfValidPasswords(List<String> passwords) {
        return (int) passwords.stream()
                .map(Day2PasswordPhilosophy::toPasswordPolicy)
                .filter(PasswordPolicy::isValid)
                .count();
    }

    public static int numberOfValidPasswords2(List<String> passwords) {
        return (int) passwords.stream()
                .map(Day2PasswordPhilosophy::toPasswordPolicy)
                .filter(PasswordPolicy::isValid2)
                .count();
    }

    private static PasswordPolicy toPasswordPolicy(String entry) {
        String[] split = entry.split("[- :]+");
        return new PasswordPolicy(split[2].charAt(0), Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[3]);
    }
}
