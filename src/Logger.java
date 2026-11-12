import java.util.Objects;

public class Logger {
    private final String name;
    private final Formatter formatter = new Formatter();

    private static LogLevel level = LogLevel.INFO;

    public Logger(String name) {
        this.name = Objects.requireNonNull(name, "Logger name cannot be null");
    }

    public static void setLevel(LogLevel newLevel) {
        level = Objects.requireNonNull(newLevel, "Log level cannot be null");
    }

    public static void setFormat(String newFormat) {
        Formatter.setFormat(newFormat);
    }

    public void log(LogLevel messageLevel, String message) {
        Objects.requireNonNull(messageLevel, "Message level cannot be null");
        Objects.requireNonNull(message, "Message cannot be null");

        if (!level.allows(messageLevel)) {
            return;
        }

        System.out.println(formatter.format(messageLevel, name, message));
    }

    public void trace(String message) {
        log(LogLevel.TRACE, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
