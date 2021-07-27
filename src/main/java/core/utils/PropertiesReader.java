package core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static Properties CONFIG = new Properties();
    private static final String CONFIG_PATH = "credential.properties";

    /**
     * Gets the values inside a properties file.
     * @param filePath url path of property file.
     * @return the read properties.
     */
    public static Properties readProperties(final String filePath) {
        Properties properties = new Properties();
        try {
            InputStream input = new FileInputStream(filePath);
            properties.load(input);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Can't read properties file.", e);
        }
        return properties;
    }

    /**
     * Gets each value of the environment value from a configuration file or from the system environment variable.
     *
     * @param variables is the value to be obtained from the environment variables
     * @return the environment value
     */
    public static String getEnv(final String variables) {
        CONFIG = readProperties(CONFIG_PATH);
        if (CONFIG.getProperty(variables) != null) {
            return CONFIG.getProperty(variables);
        }
        return System.getenv(variables);
    }
}
