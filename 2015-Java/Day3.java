import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class Day3 {
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
        HashSet<String> visited = new HashSet<>();

        for (String line : lines) {
            int x = 0;
            int y = 0;

            visited.add(x + "," + y);

            for (char move : line.toCharArray()) {
                switch (move) {
                    case '^' -> y++;
                    case 'v' -> y--;
                    case '>' -> x++;
                    case '<' -> x--;
                }

                visited.add(x + "," + y);
            }
        }

        System.out.println("Visited once or more: " + visited.size());
    }

    private static void solvePart2(List<String> lines) {
        HashSet<String> visited = new HashSet<>();

        for (String line : lines) {
            int turn = 0;
            // Santa
            int x0 = 0;
            int y0 = 0;

            // Robo-Santa
            int x1 = 0;
            int y1 = 0;

            visited.add(x0 + "," + y0);
            visited.add(x1 + "," + y1);

            for (char move : line.toCharArray()) {
                switch (move) {
                    case '^':
                        if (turn++ % 2 == 0)
                            y0++;
                        else
                            y1++;
                        break;
                    case 'v':
                        if (turn++ % 2 == 0)
                            y0--;
                        else
                            y1--;
                        break;
                    case '>':
                        if (turn++ % 2 == 0)
                            x0++;
                        else
                            x1++;
                        break;
                    case '<':
                        if (turn++ % 2 == 0)
                            x0--;
                        else
                            x1--;
                        break;
                }

                if (turn % 2 == 0)
                    visited.add(x0 + "," + y0);
                else
                    visited.add(x1 + "," + y1);
            }
        }

        System.out.println("Visited once or more: " + visited.size());
    }
}
