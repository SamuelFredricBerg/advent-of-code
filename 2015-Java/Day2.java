import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {
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
        // 2*l*w + 2*w*h + 2*h*l
        int totalArea = 0;

        for (String line : lines) {
            String[] dimensions = line.split("x");
            int l = Integer.parseInt(dimensions[0]);
            int w = Integer.parseInt(dimensions[1]);
            int h = Integer.parseInt(dimensions[2]);

            int area1 = l * w;
            int area2 = w * h;
            int area3 = h * l;

            int minArea = Math.min(area1, Math.min(area2, area3));

            totalArea += 2 * (area1 + area2 + area3) + minArea;
        }

        System.out.println("Total area: " + totalArea);
    }

    private static void solvePart2(List<String> lines) {
        int totalRibbon = 0;

        for (String line : lines) {
            String[] dimensions = line.split("x");
            int l = Integer.parseInt(dimensions[0]);
            int w = Integer.parseInt(dimensions[1]);
            int h = Integer.parseInt(dimensions[2]);

            int bow = l * w * h;

            if (l <= w && l <= h && w <= h)
                totalRibbon += 2 * (l + w) + bow;
            else if (l <= w && l <= h && h <= w)
                totalRibbon += 2 * (l + h) + bow;
            else if (w <= l && w <= h && l <= h)
                totalRibbon += 2 * (w + l) + bow;
            else if (w <= l && w <= h && h <= l)
                totalRibbon += 2 * (w + h) + bow;
            else if (h <= l && h <= w && l <= w)
                totalRibbon += 2 * (h + l) + bow;
            else
                totalRibbon += 2 * (h + w) + bow;
        }

        System.out.println("Total ribbon: " + totalRibbon);
    }
}
