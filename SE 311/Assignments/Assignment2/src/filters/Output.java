package filters;

import java.io.EOFException;

/**
 * The Output filter
 */
public class Output extends Filter {
    private final Settings settings;
    private final InputOutputType outputType;

    /**
     * Reads from its pipe, and writes to its pipe
     */
    public Output() {
        settings = Settings.getInstance();
        outputType = InputOutputType.fromString(settings.getOutputType());
    }

    /**
     * Reads from its pipe, and writes to its pipe
     */
    public void run() {
        String linesString;
        try {
            linesString = read();
        } catch (EOFException e) {
            // TODO: Auto-generated catch block
            e.printStackTrace();
            return;
        }
        switch (outputType) {
            case CONSOLE -> writeConsole(linesString);
            case FILE -> writeFile(linesString);
            default -> {
                System.out.println("Invalid output type, please specify either 'file' or 'console'");
                System.exit(1);
            }
        }
    }

    /**
     * Writes to the console
     *
     * @param s The string to write
     */
    public void writeConsole(String s) {
        System.out.println(s);
    }

    /**
     * Writes to the output file
     *
     * @param s The string to write
     */
    public void writeFile(String s) {
        String fileName = settings.getOutputFile();
        if (fileName == null) {
            System.out.println("No output file specified");
            System.exit(1);
        }
        FileHelper.writeFile(fileName, s);
    }
}
