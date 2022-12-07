package be.devriendt.advent.s2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7NoSpaceLeftOnDevice {

    private static final int MAX_SIZE = 100_000;
    private static final int DISK_TOTAL = 70_000_000;
    private static final int DISK_FREE_NEEDED = 30_000_000;

    private static final String COMMAND_INDICATOR = "$";
    private static final String ROOT_DIR = "/";
    private static final String PARENT_DIR = "..";

    private static final String DIRECTORY_SPECIFIER = "dir";

    public static int findTotalSizeOfDirectoriesToDelete(List<String> input) {
        List<File> allDirectories = walkDirectories(input);
        return allDirectories.stream()
                .map(File::getSize)
                .filter(size -> size < MAX_SIZE)
                .reduce(0, Integer::sum);
    }

    public static int findDirectoryToDelete(List<String> input) {
        List<File> allDirectories = walkDirectories(input);
        int rootFolderDiskUsage = allDirectories.stream()
                .filter(file -> file.name.equals(ROOT_DIR))
                .findFirst()
                .map(File::getSize)
                .orElseThrow(RuntimeException::new);

        int diskFree = DISK_TOTAL - rootFolderDiskUsage;
        File result = allDirectories.stream()
                .filter(file -> diskFree + file.getSize() >= DISK_FREE_NEEDED)
                .min(Comparator.comparing(File::getSize))
                .orElseThrow(RuntimeException::new);

        return result.getSize();
    }

    public static List<File> walkDirectories(List<String> input) {
        File root = File.ofDirectory(ROOT_DIR);
        File curDir = null;
        List<File> allDirectories = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.startsWith(COMMAND_INDICATOR)) {
                Command command = Command.ofLine(line);
                if (command.instruction == Instruction.CD) {
                    if (command.argument.equals(ROOT_DIR)) {
                        curDir = root;
                    } else if (command.argument.equals(PARENT_DIR)) {
                        curDir = curDir.parent;
                    } else {
                        curDir = curDir.childs.stream()
                                .filter(f -> f.name.equals(command.argument))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new);
                    }
                } else if (command.instruction == Instruction.LS) {
                    allDirectories.add(curDir);
                    String nextLine = input.get(i + 1);

                    while (!nextLine.startsWith(COMMAND_INDICATOR) && ++i < input.size()) {
                        File file = File.ofLine(nextLine);
                        file.parent = curDir;
                        curDir.childs.add(file);
                        nextLine = input.get((i + 1) % input.size());
                    }
                } else {
                    throw new IllegalArgumentException("Unrecognized command");
                }
            }
        }

        return allDirectories;
    }

    private static class Command {
        Instruction instruction;
        String argument;

        public Command(Instruction instruction, String argument) {
            this.instruction = instruction;
            this.argument = argument;
        }

        private static Command ofLine(String line) {
            String[] parts = line.split(" ");
            Instruction instr = Instruction.valueOf(parts[1].toUpperCase());
            String arg = parts.length > 2 ? parts[2] : null;
            return new Command(instr, arg);
        }
    }
    private static class File {

        String name;
        Type type;
        File parent;
        List<File> childs;
        int size;

        private File(String name, Type type, List<File> childs, int size) {
            this.name = name;
            this.type = type;
            this.childs = childs;
            this.size = size;
        }

        private static File ofLine(String line) {
            String[] parts = line.split(" ");
            if (parts[0].equals(DIRECTORY_SPECIFIER)) {
                return ofDirectory(parts[1]);
            } else {
                return new File(parts[1], Type.FILE, null, Integer.parseInt(parts[0]));
            }
        }

        private static File ofDirectory(String name) {
            return new File(name, Type.DIRECTORY, new ArrayList<>(), -1);
        }

        public int getSize() {
            return type == Type.FILE ? size : childs.stream().map(File::getSize).reduce(0, Integer::sum);
        }

        @Override
        public String toString() {
            return type + ": " + name + " - " + getSize();
        }
    }

    private enum Instruction {
        CD, LS
    }

    private enum Type {
        FILE, DIRECTORY
    }
}
