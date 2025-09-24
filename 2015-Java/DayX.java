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

    for (String line : lines) {
      System.out.println(line);
    }

    // TODO: implement Part 1
    solvePart1(lines);

    // TODO: implement Part 2
    solvePart2(lines);
  }

  private static void solvePart1(List<String> lines) {
  }

  private static void solvePart2(List<String> lines) {
  }
}
