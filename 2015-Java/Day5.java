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
            if (line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy")) {
                isNice = false;
            } else {
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

        System.out.println("Number of nice strings (Part 1): " + niceCount);
    }

    private static void solvePart2(List<String> lines) {
        boolean hasPair;
        boolean hasRepeat;
        int niceCount = 0;

        /**
         * Now, a nice string is one with all of the following properties:
         *
         * It contains a pair of any two letters that appears at least twice in the
         * string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like
         * aaa (aa, but it overlaps).
         * It contains at least one letter which repeats with exactly one letter between
         * them, like xyx, abcdefeghi (efe), or even aaa.
         *
         * For example:
         *
         * qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a
         * letter that repeats with exactly one letter between them (zxz).
         * xxyxx is nice because it has a pair that appears twice and a letter that
         * repeats with one between, even though the letters used by each rule overlap.
         * uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a
         * single letter between them.
         * ieodomkazucvgmuy is naughty because it has a repeating letter with one
         * between (odo), but no pair that appears twice.
         *
         */
        for (String line : lines) {
            hasPair = false;
            hasRepeat = false;

            // Check for pairs
            for (int i = 0; i < line.length() - 1; i++) {
                String pair = line.substring(i, i + 2);
                if (line.indexOf(pair, i + 2) != -1) {
                    hasPair = true;
                    break;
                }
            }

            // Check for repeating letters with one in between
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

        System.out.println("Number of nice strings (Part 2): " + niceCount);
    }
}
