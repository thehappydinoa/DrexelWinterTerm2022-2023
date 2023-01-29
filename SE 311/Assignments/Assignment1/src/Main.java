import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        String inputType = settings.getInputType();
        String outputType = settings.getOutputType();
        KwicIndex kwicIndex = null;
        if (inputType.equals("file")) {
            String inputFilename = settings.getInputFilename();
            try {
                kwicIndex = KwicIndex.fromFile(inputFilename);
            } catch (FileNotFoundException e) {
                ConsoleOutput.writeError("Could not find file " + inputFilename);
                System.exit(1);
            }
            AlphabeticalSorter alphabeticalSorter = new AlphabeticalSorter();
            alphabeticalSorter.sort(kwicIndex);
            if (outputType.equals("console")) {
                ConsoleOutput consoleOutput = new ConsoleOutput();
                consoleOutput.write(kwicIndex.getSentences());
            } else if (outputType.equals("file")) {
                String outputFilename = settings.getOutputFilename();
                FileOutput fileOutput = new FileOutput(outputFilename);
                fileOutput.write(kwicIndex.getSentences());
            } else {
                ConsoleOutput.writeError("Invalid output type: " + outputType);
            }
        } else {
            ConsoleOutput.writeError("Invalid input type: " + inputType);
        }
    }
}