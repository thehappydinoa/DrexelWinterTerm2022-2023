import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private static final String USER_PROPERTIES_FILE_NAME = "settings.properties";
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "default.properties";
    private final Properties properties;

    public Settings() {
        properties = loadProperties(USER_PROPERTIES_FILE_NAME, DEFAULT_PROPERTIES_FILE_NAME);
    }

    public Properties loadProperties(String propertiesFile, String defaultPropertiesFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties defaultProperties = new Properties();
        try {
            InputStream defaultPropertiesStream = classLoader.getResourceAsStream(defaultPropertiesFile);
            defaultProperties.load(defaultPropertiesStream);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        Properties properties = new Properties(defaultProperties);
        try {
            InputStream propertiesStream = classLoader.getResourceAsStream(propertiesFile);
            properties.load(propertiesStream);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return properties;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
