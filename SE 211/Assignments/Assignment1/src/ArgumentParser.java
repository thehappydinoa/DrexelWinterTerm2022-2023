public class ArgumentParser {
    private String inputFileName = "";
    private String stopWordsFileName = "";

    public ArgumentParser(String[] args) {
        // Get -s [stop words file name] and positional arg [input file name]
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-s")) {
                if (i < args.length - 1) {
                    stopWordsFileName = args[i + 1];
                }
                i++;
            } else {
                inputFileName = args[i];
            }
        }
        try {
            validate();
        } catch (IllegalArgumentException e) {
            printHelp();
            throw e;
        }
    }

    private void validate() {
        if (inputFileName.equals("")) {
            throw new IllegalArgumentException("Input file name is required");
        }
    }

    private void printHelp() {
        System.out.println("Usage: kwic [-s stop_words_file] input_file");
        System.out.println("  -s stop_words_file\tFile containing stop words");
        System.out.println("  input_file\t\t\tFile containing input text");
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public boolean hasStopWordsFileName() {
        return !stopWordsFileName.isEmpty();
    }

    public String getStopWordsFileName() {
        return stopWordsFileName;
    }
}
