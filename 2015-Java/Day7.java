import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day7 {
    private static HashMap<String, String[]> instructions = new HashMap<>();
    private static HashMap<String, Integer> cache = new HashMap<>();
    private static int result1;

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
        parseInput(lines);

        result1 = evaluate("a");

        System.out.println("(Part 1): Wire 'a' value: " + result1);
    }

    private static void solvePart2(List<String> lines) {
        cache.clear();
        instructions.put("b", new String[] { String.valueOf(result1) });

        int result2 = evaluate("a");

        System.out.println("(Part 2): Wire 'a' after recompute: " + result2);
    }

    private static void parseInput(List<String> lines) {
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split("->");
            String src = parts[0].trim();
            String dst = parts[1].trim();
            instructions.put(dst, src.split(" "));
        }
    }

    private static int evaluate(String wire) {
        if (wire.matches("\\d+")) {
            return Integer.valueOf(wire);
        }

        if (cache.containsKey(wire)) {
            return cache.get(wire);
        }

        String[] tokens = instructions.get(wire);
        int result;

        if (tokens.length == 1) {
            result = val(tokens[0]);
        } else if (tokens.length == 2 && tokens[0].equals("NOT")) {
            result = (~val(tokens[1]));
        } else {
            String left = tokens[0];
            String op = tokens[1];
            String right = tokens[2];
            int a = val(left);
            int b = val(right);

            switch (op) {
                case "AND":
                    result = (a & b);
                    break;
                case "OR":
                    result = (a | b);
                    break;
                case "LSHIFT":
                    result = (a << Integer.valueOf(right));
                    break;
                case "RSHIFT":
                    result = (a >> Integer.valueOf(right));
                    break;
                default:
                    result = -1;
                    break;

            }
        }

        cache.put(wire, result);
        return result;
    }

    private static int val(String token) {
        if (token.matches("\\d+")) {
            return Integer.valueOf(token);
        } else {
            return evaluate(token);
        }
    }
}
