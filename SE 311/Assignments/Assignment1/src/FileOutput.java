import java.io.File;

public class FileOutput extends Output {
    private String filename;
    private File file;

    public FileOutput(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    public void write(String s) {
        System.out.println("FileOutput.write(" + s + ")");
    }
}
