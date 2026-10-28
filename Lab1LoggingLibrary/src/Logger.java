import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Logger {
    private final String name;

    private static LogLevel level = LogLevel.INFO;
    private static String format = "[%t] [%l] %n: %m";

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public Logger(String name) {
        this.name = Objects.requireNonNull(name, "Logger name cannot be null");
    }

    public static void setLevel(LogLevel newLevel) {
        level = Objects.requireNonNull(newLevel, "Log level cannot be null");
    }

    public static void setFormat(String newFormat) {
        format = Objects.requireNonNull(newFormat, "Log format cannot be null");
    }

    public void log(LogLevel messageLevel, String message) {
        Objects.requireNonNull(messageLevel, "Message level cannot be null");
        Objects.requireNonNull(message, "Message cannot be null");

        if (!level.allows(messageLevel)) {
            return;
        }

        System.out.println(formatMessage(messageLevel, message));
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

    private String formatMessage(LogLevel messageLevel, String message) {
        String template = format;
        String time = LocalTime.now().format(TIME_FORMAT);
        String randomPhrase = null;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < template.length(); i++) {
            char current = template.charAt(i);

            if (current != '%' || i + 1 >= template.length()) {
                result.append(current);
                continue;
            }

            char placeholder = template.charAt(i + 1);

            switch (placeholder) {
                case 't':
                    result.append(time);
                    break;
                case 'l':
                    result.append(messageLevel.name());
                    break;
                case 'n':
                    result.append(name);
                    break;
                case 'm':
                    result.append(message);
                    break;
                case 'r':
                    if (randomPhrase == null) {
                        randomPhrase = messageLevel.randomPhrase();
                    }

                    result.append(randomPhrase);
                    break;
                default:
                    result.append('%').append(placeholder);
                    break;
            }

            i++;
        }

        return result.toString();
    }
}