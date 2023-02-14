package filters;

import java.io.EOFException;

public class Output extends Filter {
    Settings settings;
    InputOutputType outputType;

    public Output() {
        settings = Settings.getInstance();
        outputType = InputOutputType.fromString(settings.getOutputType());
    }
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
            case CONSOLE:
                writeConsole(linesString);
                break;
            case FILE:
                writeFile(linesString);
                break;
            default:
                System.out.println("Invalid output type, please specify either 'file' or 'console'");
                System.exit(1);
        }
    }

    public void writeConsole(String s) {
        System.out.println(s);
    }

    public void writeFile(String s) {
        String fileName = settings.getOutputFile();
        if (fileName == null) {
            System.out.println("No output file specified");
            System.exit(1);
        }
        FileHelper.writeFile(fileName, s);
    }
}
