package utils;
import java.io.FileInputStream;
import java.util.Properties;
public class ConfigReader {
	private static ConfigReader instance;
    private Properties props;

    private ConfigReader() {
        try {
            props = new Properties();
            FileInputStream fis = new FileInputStream(
                "src/test/resources/config.properties");
            props.load(fis);
        } catch (Exception e) {
            throw new RuntimeException(
                "config.properties not found!", e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public static String get(String key) {
        return getInstance().props.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}

