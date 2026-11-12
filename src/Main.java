public class Main {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();

        Formatter.setFormat("[%l] %n: %m");
        System.out.println(formatter.format(LogLevel.INFO, "Parentheses", "Ratio is 100%n"));

        Formatter.setFormat("%n | %m");
        System.out.println(formatter.format(LogLevel.WARN, "Parser", "Unexpected input"));

        Logger.setLevel(LogLevel.TRACE);
        Logger.setFormat("[%l] %n: %m | %r");

        ParenthesesValidator validator = new ParenthesesValidator();

        String[] inputs = {
                "()",
                ")((()))",
                "(",
                "(())((()())())"
        };

        for (String input : inputs) {
            validator.isValid(input);
        }
    }
}
