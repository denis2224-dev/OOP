public class Main {
    public static void main(String[] args) {
        Logger firstLogger = new Logger("First");
        Logger secondLogger = new Logger("Second");

        Logger.setLevel(LogLevel.INFO);

        firstLogger.log(LogLevel.TRACE, "This should be hidden");
        firstLogger.log(LogLevel.INFO, "Application started");
        secondLogger.log(LogLevel.WARN, "Something unexpected happened");

        Logger.setLevel(LogLevel.ERROR);

        firstLogger.log(LogLevel.INFO, "This should also be hidden");
        secondLogger.log(LogLevel.WARN, "This should also be hidden");

        firstLogger.log(LogLevel.ERROR, "First operation failed");
        secondLogger.log(LogLevel.ERROR, "Second operation failed");
    }
}