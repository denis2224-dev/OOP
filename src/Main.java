public class Main {
    public static void main(String[] args) {
        Logger.setLevel(LogLevel.TRACE);
        Formatter.setFormat("[%l] %n: %m | %r");

        Logger standalone = new Logger("Standalone");
        standalone.info("Default logger ready");

        Formatter sharedFormatter = new Formatter();
        ConsoleSink sharedSink = new ConsoleSink();
        Logger demoLogger = new Logger("Demo", sharedFormatter, sharedSink);
        Logger validatorLogger = new Logger("Parentheses", sharedFormatter, sharedSink);

        ParenthesesValidator validator = new ParenthesesValidator(validatorLogger);
        String[] inputs = {"()", ")((()))", "(", "(())((()())())"};

        demoLogger.info("Checking sample inputs");
        for (String input : inputs) {
            validator.isValid(input);
        }
    }
}