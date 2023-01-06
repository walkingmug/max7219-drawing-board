import java.io.*;

public class SaveResult {
    public static void main(String[] args) {
        String source = args[0];
        String replacement = args[1];

        File sourceFile = new File(source);
        File destinationDir = new File("result");
        File destination = new File(destinationDir, "result.ino");

        // Create the destination folder if it doesn't exist
        if (!destinationDir.exists()) {
            destinationDir.mkdir();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line.replace("//input", replacement));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
