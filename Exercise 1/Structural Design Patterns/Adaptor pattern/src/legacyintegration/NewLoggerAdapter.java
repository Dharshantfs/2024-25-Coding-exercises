package legacyintegration;

public class NewLoggerAdapter implements LegacyLogger {
    private NewLogger newLogger;

    public NewLoggerAdapter(NewLogger newLogger) {
        this.newLogger = newLogger;
    }

    @Override
    public void logMessage(String message) {
        newLogger.writeLog(message);
    }
}
