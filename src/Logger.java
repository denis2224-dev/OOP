import java.util.Objects;

public class Logger {
    private static LogLevel level = LogLevel.INFO;

    private final String name;
    private final Formatter formatter;
    private final ConsoleSink sink;

    public Logger(String name) {
        this(name, new Formatter(), new ConsoleSink());
    }

    public Logger(String name, Formatter formatter, ConsoleSink sink) {
        this.name = Objects.requireNonNull(name, "Logger name cannot be null");
        this.formatter = Objects.requireNonNull(formatter, "Formatter cannot be null");
        this.sink = Objects.requireNonNull(sink, "Sink cannot be null");
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

        String line = formatter.format(messageLevel, name, message);
        sink.write(line);
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
