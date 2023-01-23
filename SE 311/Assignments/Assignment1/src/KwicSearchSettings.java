public class KwicSearchSettings extends Settings {
    public String getInputType() {
        return get("input_type");
    }

    public String getInputFilename() {
        return get("input_filename");
    }

    public String getOutputType() {
        return get("output_type");
    }

    public String getOutputFilename() {
        return get("output_filename");
    }
}
