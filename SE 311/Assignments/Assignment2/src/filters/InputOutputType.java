package filters;

public enum InputOutputType {
    FILE, CONSOLE;

    // Create a static method to get the enum from a string
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
