import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {
    private static int i = 0;

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

        for (String line : lines) {
            while (true) {
                String testString = line + String.valueOf(i);

                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] hash = md.digest(testString.getBytes());
                    StringBuilder sb = new StringBuilder();

                    for (byte b : hash)
                        sb.append(String.format("%02x", b));

                    if (sb.toString().startsWith("00000")) {
                        System.out.println("Found: " + testString + " -> " + sb.toString());
                        break;
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    private static void solvePart2(List<String> lines) {
        for (String line : lines) {
            while (true) {
                String testString = line + String.valueOf(i);

                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] hash = md.digest(testString.getBytes());
                    StringBuilder sb = new StringBuilder();

                    for (byte b : hash)
                        sb.append(String.format("%02x", b));

                    if (sb.toString().startsWith("000000")) {
                        System.out.println("Found: " + testString + " -> " + sb.toString());
                        break;
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }
}
