package filters;

public class Input extends Filter {
    InputOutputType inputType;


    public Input() {
        Settings settings = Settings.getInstance();
        inputType = InputOutputType.fromString(settings.getInputType());
        setOut(new Pipe());
    }

    public void run() {
        switch (inputType) {
            case FILE:
                readFile();
                break;
            case CONSOLE:
                readConsole();
                break;
            default:
                System.out.println("Invalid input type, please specify either 'file' or 'console'");
                System.exit(1);
        }
    }

    public void readFile() {
        String fileName = Settings.getInstance().getInputFile();
        if (fileName == null) {
            System.out.println("No input file specified");
            System.exit(1);
        }
        String fileContents = FileHelper.readFile(fileName);
        write(fileContents);
    }

    private void readConsole() {
        System.out.println("Enter text to be filtered, press enter twice to finish");
        String input = "";
        String line;
        while (true) {
            line = System.console().readLine();
            if (line.equals("")) {
                break;
            }
            input += line + "\r\n";
        }
        write(input);
    }
}
