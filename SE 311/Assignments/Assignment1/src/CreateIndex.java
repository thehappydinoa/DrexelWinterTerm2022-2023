import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateIndex {
    private static final CircularShifter circularShifter = new CircularShifter();
    private static final AlphabeticalSorter alphabeticalSorter = new AlphabeticalSorter();
    public static void main(String[] args) {
        // Read settings
        Settings settings = new Settings();

        // Read input
        Input input = getInput(settings);

        // Create index
        KwicIndex kwicIndex;
        try {
            kwicIndex = input.read();
        } catch (IOException e) {
            ConsoleOutput.writeError("Could not read input");
            System.exit(1);
            return;
        }

        // Shift and sort
        circularShifter.shift(kwicIndex);
        alphabeticalSorter.sort(kwicIndex);

        // Write output
        Output output = getOutput(settings);
        if (output == null) {
            System.exit(1);
        }
        output.write(kwicIndex.getSentences());
    }

    private static Input getInput(Settings settings) {
        // Read input type
        String inputType = settings.getInputType();

        // Create input
        Input input = null;
        if (inputType.equals(FileInput.INPUT_TYPE)) {
            String inputFilename = settings.getInputFilename();
            try {
                input = new FileInput(inputFilename);
            } catch (FileNotFoundException e) {
                ConsoleOutput.writeError("Could not find file " + inputFilename);
                System.exit(1);
            }
        } else if (inputType.equals(ConsoleInput.INPUT_TYPE)) {
            input = new ConsoleInput();
        } else {
            ConsoleOutput.writeError("Invalid input type: " + inputType);
        }
        return input;
    }
    private static Output getOutput(Settings settings) {
        // Read output type
        String outputType = settings.getOutputType();

        // Create output
        Output output = null;
        if (outputType.equals(ConsoleOutput.OUTPUT_TYPE)) {
            output = new ConsoleOutput();
        } else if (outputType.equals(FileOutput.OUTPUT_TYPE)) {
            output = new FileOutput(settings.getOutputFilename());
        } else {
            ConsoleOutput.writeError("Invalid output type: " + outputType);
        }
        return output;
    }
}
