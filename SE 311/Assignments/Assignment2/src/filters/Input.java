package filters;

/**
 * The input filter
 */
public class Input extends Filter {
    private final InputOutputType inputType;


    /**
     * Creates a new input filter
     */
    public Input() {
        Settings settings = Settings.getInstance();
        inputType = InputOutputType.fromString(settings.getInputType());
        setOut(new Pipe());
    }

    /**
     * Reads from the input and writes to its pipe
     */
    public void run() {
        switch (inputType) {
            case FILE -> readFile();
            case CONSOLE -> readConsole();
            default -> {
                System.out.println("Invalid input type, please specify either 'file' or 'console'");
                System.exit(1);
            }
        }
    }

    /**
     * Reads from the input file and writes to its pipe
     */
    public void readFile() {
        String fileName = Settings.getInstance().getInputFile();
        if (fileName == null) {
            System.out.println("No input file specified");
            System.exit(1);
        }
        String fileContents = FileHelper.readFile(fileName);
        write(fileContents);
    }

    /**
     * Reads from the console and writes to its pipe
     */
    private void readConsole() {
        System.out.println("Enter text to be filtered, press enter twice to finish");
        StringBuilder input = new StringBuilder();
        String line;
        while (true) {
            line = System.console().readLine().trim();
            if (line.equals("")) {
                break;
            }
            input.append(line).append("\r\n");
        }
        write(input.toString());
    }
}
