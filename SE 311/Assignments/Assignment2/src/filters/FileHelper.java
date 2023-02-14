package filters;

import java.io.*;

public class FileHelper {
    public static String readFile(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
            return null;
        }
        StringBuilder fileContents = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return fileContents.toString();
    }

    public static void writeFile(String fileName, String contents) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(1);
            return;
        }
        try {
            writer.write(contents);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
