import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day8 {
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

        solvePart1(lines);

        solvePart2(lines);
    }

    private static void solvePart1(List<String> lines) {
        int totalDiff = 0;

        for (String line : lines) {
            int codeLen = line.length();
            int memLen = 0;

            for (int i = 1; i < codeLen - 1;) {
                char c = line.charAt(i);
                if (c == '\\') {
                    i++;
                    char next = line.charAt(i);
                    if (next == '\\' || next == '"') {
                        memLen += 1;
                    } else if (next == 'x' && i + 2 < codeLen) {
                        i += 2;
                        memLen += 1;
                    }
                } else {
                    memLen += 1;
                }
                i++;
            }

            totalDiff += (codeLen - memLen);
        }

        System.out.println("(Part 1): Difference in 'code length - memory length': " + totalDiff);
    }

    private static void solvePart2(List<String> lines) {
        int totalDiff = 0;

        for (String line : lines) {
            int codeLen = line.length();
            int encodedLen = 2;

            for (char c : line.toCharArray()) {
                encodedLen += (c == '"' || c == '\\') ? 2 : 1;
            }

            totalDiff += (encodedLen - codeLen);
        }

        System.out.println("(Part 2): Difference in 'encoded length - code length': " + totalDiff);
    }
}
