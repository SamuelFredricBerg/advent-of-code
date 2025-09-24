import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6 {
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
        boolean[][] lights = new boolean[1000][1000];
        int count = 0;

        for (String line : lines) {
            String[] parts = line.split(" ");
            String command = "";
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;

            if (parts[0].equals("toggle")) {
                command = "toggle";
                String[] start = parts[1].split(",");
                String[] end = parts[3].split(",");
                x1 = Integer.parseInt(start[0]);
                y1 = Integer.parseInt(start[1]);
                x2 = Integer.parseInt(end[0]);
                y2 = Integer.parseInt(end[1]);
            } else {
                command = parts[1];
                String[] start = parts[2].split(",");
                String[] end = parts[4].split(",");
                x1 = Integer.parseInt(start[0]);
                y1 = Integer.parseInt(start[1]);
                x2 = Integer.parseInt(end[0]);
                y2 = Integer.parseInt(end[1]);
            }

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    switch (command) {
                        case "on":
                            lights[i][j] = true;
                            break;
                        case "off":
                            lights[i][j] = false;
                            break;
                        case "toggle":
                            lights[i][j] = !lights[i][j];
                            break;
                    }
                }
            }
        }

        for (boolean[] row : lights) {
            for (boolean light : row) {
                if (light) {
                    count++;
                }
            }
        }

        System.out.println("(Part 1): Number of lights on: " + count);
    }

    private static void solvePart2(List<String> lines) {
        int[][] lights = new int[1000][1000];
        int totalBrightness = 0;

        for (String line : lines) {
            String[] parts = line.split(" ");
            String command;
            int x1, y1, x2, y2;

            if (parts[0].equals("toggle")) {
                command = "toggle";
                String[] start = parts[1].split(",");
                String[] end = parts[3].split(",");
                x1 = Integer.parseInt(start[0]);
                y1 = Integer.parseInt(start[1]);
                x2 = Integer.parseInt(end[0]);
                y2 = Integer.parseInt(end[1]);
            } else {
                command = parts[1];
                String[] start = parts[2].split(",");
                String[] end = parts[4].split(",");
                x1 = Integer.parseInt(start[0]);
                y1 = Integer.parseInt(start[1]);
                x2 = Integer.parseInt(end[0]);
                y2 = Integer.parseInt(end[1]);
            }

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    switch (command) {
                        case "on":
                            lights[i][j] += 1;
                            break;
                        case "off":
                            lights[i][j] = Math.max(0, lights[i][j] - 1);
                            break;
                        case "toggle":
                            lights[i][j] += 2;
                            break;
                    }
                }
            }
        }

        for (int[] row : lights) {
            for (int brightness : row) {
                totalBrightness += brightness;
            }
        }

        System.out.println("(Part 2): Total brightness: " + totalBrightness);
    }
}
