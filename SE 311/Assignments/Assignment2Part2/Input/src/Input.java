public class Input extends Filter {
    private final InputOutputType inputType;

    public Input() {
        Settings settings = Settings.getInstance();
        this.inputType = InputOutputType.fromString(settings.getInputType());
    }

    public void run() {
        switch (this.inputType) {
            case FILE:
                this.readFile();
                break;
            case CONSOLE:
                this.readConsole();
                break;
            default:
                System.err.println("Invalid input type, please specify either 'file' or 'console'");
                System.exit(1);
        }

    }

    public void readFile() {
        String fileName = Settings.getInstance().getInputFile();
        if (fileName == null) {
            System.err.println("No input file specified");
            System.exit(1);
        }

        String fileContents = FileHelper.readFile(fileName);
        this.write(fileContents);
    }

    private void readConsole() {
        write(read());
    }
}
