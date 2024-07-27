package legacyintegration;

public class LegacySystemIntegrationDemo {
    public static void main(String[] args) {
        // Create an instance of NewLogger
        NewLogger newLogger = new NewLogger();

        // Create an adapter for NewLogger to be used as LegacyLogger
        LegacyLogger legacyLogger = new NewLoggerAdapter(newLogger);

        // Use the adapter
        legacyLogger.logMessage("This is a test log message.");
    }
}
