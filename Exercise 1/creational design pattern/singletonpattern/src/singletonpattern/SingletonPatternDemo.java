package singletonpattern;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        // Get the single instance of ConfigurationManager
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        System.out.println("Configuration 1: " + config1.getConfiguration());

        // Set a new configuration
        config1.setConfiguration("New Configuration");

        // Get the same instance of ConfigurationManager
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("Configuration 2: " + config2.getConfiguration());

        // Check if both references point to the same instance
        if (config1 == config2) {
            System.out.println("Both config1 and config2 are the same instance.");
        }
    }
}
