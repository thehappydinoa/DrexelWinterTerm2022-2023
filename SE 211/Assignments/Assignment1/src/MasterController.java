public class MasterController {
    public static void main(String[] args) {
        try {
            ArgumentParser argumentParser = new ArgumentParser(args);
            LineStorage lineStorage = new LineStorage();
            Output output = new Output(lineStorage);
            Input input = new Input(argumentParser.getInputFileName());
            input.addLines(lineStorage);
            CircularShift.shift(lineStorage);
            Alphabetizer.sort(lineStorage);
            output.out();
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
            System.exit(1);
        }
    }
}
