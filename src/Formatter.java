import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Formatter {

    private static String format = "[%t] [%l] %n: %m";
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void setFormat(String newFormat) {
        format = Objects.requireNonNull(newFormat, "Log format cannot be null");
    }

    public String format(LogLevel messageLevel, String name, String message) {
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
