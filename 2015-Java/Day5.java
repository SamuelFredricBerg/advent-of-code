import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day5 {
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
        boolean isNice = false;
        int niceCount = 0;

        for (String line : lines) {
            if (line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy"))
                isNice = false;
            else {
                int vowelCount = 0;
                boolean hasDoubleLetter = false;

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if ("aeiou".indexOf(c) != -1) {
                        vowelCount++;
                    }
                    if (i > 0 && c == line.charAt(i - 1)) {
                        hasDoubleLetter = true;
                    }
                }

                if (vowelCount >= 3 && hasDoubleLetter) {
                    isNice = true;
                } else {
                    isNice = false;
                }
            }

            if (isNice) {
                niceCount++;
            }
        }

        System.out.println("(Part 1): Number of nice strings: " + niceCount);
    }

    private static void solvePart2(List<String> lines) {
        boolean hasPair;
        boolean hasRepeat;
        int niceCount = 0;

        for (String line : lines) {
            hasPair = false;
            hasRepeat = false;

            for (int i = 0; i < line.length() - 1; i++) {
                String pair = line.substring(i, i + 2);
                if (line.indexOf(pair, i + 2) != -1) {
                    hasPair = true;
                    break;
                }
            }

            for (int i = 0; i < line.length() - 2; i++) {
                if (line.charAt(i) == line.charAt(i + 2)) {
                    hasRepeat = true;
                    break;
                }
            }

            if (hasPair && hasRepeat) {
                niceCount++;
            }

        }

        System.out.println("(Part 2): Number of nice strings: " + niceCount);
    }
}
