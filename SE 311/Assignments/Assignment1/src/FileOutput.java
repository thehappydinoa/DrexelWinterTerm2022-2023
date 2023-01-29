import java.io.File;
import java.util.ArrayList;

public class FileOutput extends Output {
    private String filename;
    private File file;

    public FileOutput(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    private void tryCreateFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                ConsoleOutput.writeError("Could not create file " + filename);
                System.exit(1);
            }
        }
    }

    public void write(String s) {
        tryCreateFile();
        try {
            java.io.FileWriter fw = new java.io.FileWriter(file, true);
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
            java.io.FileWriter fw = new java.io.FileWriter(file, true);
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
