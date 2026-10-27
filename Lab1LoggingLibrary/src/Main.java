public class Main {
    public static void main(String[] args) {
        Logger firstLogger = new Logger("First");
        Logger secondLogger = new Logger("Second");

        Logger.setLevel(LogLevel.INFO);

        firstLogger.log(LogLevel.TRACE, "This should be hidden");
        firstLogger.log(LogLevel.INFO, "Application started");

        Logger.setFormat("[%l] %n: %m");

        firstLogger.log(LogLevel.WARN, "Check the input");
        secondLogger.log(LogLevel.ERROR, "Operation failed");

        Logger.setFormat("%m");

        firstLogger.log(
                LogLevel.INFO,
                "These is literal message text: %l %n %t"
        );

        Logger.setFormat("[%l] [%l] %m | %x");

        secondLogger.log(LogLevel.INFO, "Repeated placeholders work");

        Logger.setFormat("[%l] %m");
        Logger.setLevel(LogLevel.ERROR);

        firstLogger.log(LogLevel.WARN, "This should be hidden");
        secondLogger.log(LogLevel.ERROR, "Only errors are visible now");
    }
}