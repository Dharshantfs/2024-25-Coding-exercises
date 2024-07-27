package singletonpattern;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private String configuration;

    // Private constructor to prevent instantiation
    private ConfigurationManager() {
        this.configuration = "Default Configuration";
    }

    // Public method to provide access to the instance
    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    // Method to get configuration settings
    public String getConfiguration() {
        return configuration;
    }

    // Method to set configuration settings
    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}
