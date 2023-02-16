import java.io.InputStream;
import java.util.Properties;

/**
 * The settings class reads the properties from the settings.properties file.
 */
public class Settings {
    private static final String PROPERTIES_FILE = "settings.properties";
    private final Properties properties;

    private static Settings instance = null;

    /**
     * Reads the properties from the settings.properties file
     */
    private Settings() {
        ClassLoader classLoader = getClass().getClassLoader();
        properties = new Properties();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(PROPERTIES_FILE);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Returns the singleton instance of the Settings class
     *
     * @return The singleton instance of the Settings class
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    /**
     * Gets a property from the settings.properties file
     *
     * @param key The key of the property
     * @return The value of the property
     */
    public String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets a property from the settings.properties file
     *
     * @param key          The key of the property
     * @param defaultValue The default value of the property
     * @return The value of the property
     */
    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Gets the output type from the settings.properties file
     *
     * @return The output type
     */
    public String getOutputType() {
        return get("output.type", "console");
    }

    /**
     * Gets the output file from the settings.properties file
     *
     * @return The output file
     */
    public String getOutputFile() {
        return get("output.file", "index.txt");
    }
}
