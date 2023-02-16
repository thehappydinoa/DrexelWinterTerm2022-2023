/**
 * The enum for the input and output types
 */
public enum InputOutputType {
    FILE, CONSOLE;

    /**
     * Gets the enum from a string
     *
     * @param inputType The string to get the enum from
     * @return The enum
     */
    public static InputOutputType fromString(String inputType) {
        if (inputType == null) {
            return null;
        }
        for (InputOutputType type : InputOutputType.values()) {
            if (inputType.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        return null;
    }
}
