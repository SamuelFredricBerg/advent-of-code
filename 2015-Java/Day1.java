import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide the input file name as the first argument.");
            System.exit(1);
        }

        String fileName = args[0];
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // for (String line : lines) {
        // System.out.println(line);
        // }

        solvePart1(lines);

        solvePart2(lines);
    }

    private static void solvePart1(List<String> lines) {
        int floor = 0;

        for (String line : lines) {
            for (char c : line.toCharArray()) {
                if (c == '(') {
                    floor++;
                } else if (c == ')') {
                    floor--;
                }
            }
        }

        System.out.println("Final Floor: " + floor);
    }

    private static void solvePart2(List<String> lines) {
        int floor = 0;
        int position = 0;
        boolean basementReached = false;

        for (String line : lines) {
            for (char c : line.toCharArray()) {
                position++;
                if (c == '(') {
                    floor++;
                } else if (c == ')') {
                    floor--;
                }

                if (floor < 0 && !basementReached) {
                    basementReached = true;
                    System.out.println("Position of first entry to basement: " + position);
                    return;
                }
            }
        }
    }

}
