package filters;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class is used to read the settings from the settings.properties file.
 */
public class Settings {
    private static final String PROPERTIES_FILE = "settings.properties";
    private final Properties properties;

    private static Settings instance = null;

    public Settings() {
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

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getInputType() {
        return get("input.type", "console");
    }

    public String getInputFile() {
        return get("input.file");
    }

    public String getOutputType() {
        return get("output.type", "console");
    }

    public String getOutputFile() {
        return get("output.file", "index.txt");
    }

    public ArrayList<String> getStopWords() {
        String stopWords = get("stop.words");
        String[] words = stopWords.split(",");
        ArrayList<String> stopWordsList = new ArrayList<>();
        for (String word : words) {
            stopWordsList.add(word.toLowerCase().trim());
        }
        return stopWordsList;
    }
}
