public class Main {
    public static void main(String[] args) {
        KwicSearchSettings settings = new KwicSearchSettings();
        String inputType = settings.getInputType();
        String outputType = settings.getOutputType();

        System.out.println("settings.getInputType() = " + inputType);
        System.out.println("settings.getInputFilename() = " + settings.getInputFilename());
        System.out.println("settings.getOutputType() = " + outputType);
        System.out.println("settings.getOutputFilename() = " + settings.getOutputFilename());
    }
}