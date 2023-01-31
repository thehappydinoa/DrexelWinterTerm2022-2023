import java.io.File;
import java.util.ArrayList;

public class FileOutput extends Output {
    public static final String OUTPUT_TYPE = "file";
    private final String filename;
    private final File file;

    public FileOutput(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    public static void createDir(String newDirPath) {
        File newDir = new File(newDirPath);
        if (!newDir.exists()) {
            boolean success = newDir.mkdir();
            if (!success) {
                ConsoleOutput.writeError("Could not create directory " + newDirPath);
                System.exit(1);
            }
        }
    }

    private void tryCreateFile() {
        if (!file.exists()) {
            try {
                boolean success = file.createNewFile();
                if (!success) {
                    ConsoleOutput.writeError("Could not create file " + filename);
                    System.exit(1);
                }
            } catch (Exception e) {
                ConsoleOutput.writeError("Could not create file " + filename);
                System.exit(1);
            }
        }
    }

    public void write(String s) {
        tryCreateFile();
        try {
            java.io.FileWriter fw = new java.io.FileWriter(file, false);
            fw.write(s + "\n");
            fw.close();
        } catch (Exception e) {
            ConsoleOutput.writeError("Could not write to file " + filename);
            System.exit(1);
        }
    }

    public void write(ArrayList<String> s) {
        tryCreateFile();
        try {
            java.io.FileWriter fw = new java.io.FileWriter(file, false);
            for (String line : s) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (Exception e) {
            ConsoleOutput.writeError("Could not write to file " + filename);
            System.exit(1);
        }
    }
}
