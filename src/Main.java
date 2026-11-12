public class Main {
    public static void main(String[] args) {
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