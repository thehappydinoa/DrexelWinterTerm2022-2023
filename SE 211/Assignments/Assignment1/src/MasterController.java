import java.io.FileNotFoundException;

public class MasterController {
    public static void main(String[] args) {
        try {
            ArgumentParser argumentParser = new ArgumentParser(args);
            LineStorage lineStorage = new LineStorage();
            try {
                Input input = new Input(argumentParser.getInputFileName());
                lineStorage.addLines(input.readlines());
            } catch (FileNotFoundException e) {
                Output.error("The file " + argumentParser.getInputFileName() + " does not exist");
            }
            if (lineStorage.getLines().size() == 0) {
                Output.error("The file " + argumentParser.getInputFileName() + " is empty");
            }
            if (argumentParser.hasStopWordsFileName()) {
                String stopWordsFileName = argumentParser.getStopWordsFileName();
                try {
                    Input stopWordsInput = new Input(stopWordsFileName);
                    lineStorage.addStopWords(stopWordsInput.readlines());
                } catch (FileNotFoundException e) {
                    Output.warn("The file " + stopWordsFileName + " does not exist");
                }
                if (lineStorage.getStopWords().size() == 0) {
                    Output.warn("The file " + stopWordsFileName + " is empty");
                }
            }
            CircularShift.shift(lineStorage);
            Alphabetizer.sort(lineStorage);
            Output.printLines(lineStorage);
        } catch (Exception e) {
            Output.error(e.getMessage());
        }
    }
}
