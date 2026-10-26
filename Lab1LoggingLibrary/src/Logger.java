import java.util.Objects;

public class Logger {
    private String name;

    private static LogLevel level = LogLevel.INFO;

    public Logger(String name) {
        this.name = Objects.requireNonNull(name, "Logger name cannot be null");
    }

    public static void setLevel(LogLevel newLevel) {
        level = Objects.requireNonNull(newLevel, "Log level cannot be null");
    }

    public void log(LogLevel messageLevel, String message) {
        Objects.requireNonNull(messageLevel, "Message level cannot be null");
        Objects.requireNonNull(message, "Message cannot be null");

        if (!level.allows(messageLevel)) {
            return;
        }

        System.out.println("[" + messageLevel + "] " + name + ": " + message);
    }
}
