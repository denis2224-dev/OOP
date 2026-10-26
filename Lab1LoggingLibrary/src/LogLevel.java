public enum LogLevel {
    TRACE(10),
    INFO(20),
    WARN(30),
    ERROR(40);

    private final int severity;

    LogLevel(int severity) {
        this.severity = severity;
    }

    public boolean allows(LogLevel messageLevel) {
        return messageLevel.severity >= this.severity;
    }
}