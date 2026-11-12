import java.util.concurrent.ThreadLocalRandom;

public enum LogLevel {
    TRACE(
            10,
            "Following program steps",
            "Inspecting execution",
            "Tracking progress"
    ),
    INFO(
            20,
            "Here is your result",
            "Progress update",
            "Event recorded"
    ),
    WARN(
            30,
            "Check this situation",
            "Something needs attention",
            "Proceed with care"
    ),
    ERROR(
            40,
            "Operation failed",
            "Invalid input detected",
            "A problem occurred"
    );

    private final int severity;
    private final String[] phrases;

    LogLevel(int severity, String... phrases) {
        this.severity = severity;
        this.phrases = phrases;
    }

    public boolean allows(LogLevel messageLevel) {
        return messageLevel.severity >= this.severity;
    }

    public String randomPhrase() {
        int index = ThreadLocalRandom.current().nextInt(phrases.length);
        return phrases[index];
    }
}