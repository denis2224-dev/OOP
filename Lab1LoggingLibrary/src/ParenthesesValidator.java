public class ParenthesesValidator {
    private final Logger logger = new Logger("Parentheses");

    public boolean isValid(String input) {
        if (input == null) {
            return reportInvalid(input, "Input cannot be null");
        }

        int balance = 0;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (current == '(') {
                balance++;

                logger.trace(
                        "Opening parenthesis found at index " + i
                                + "; unmatched openings: " + balance
                );
            } else if (current == ')') {
                if (balance == 0) {
                    logger.warn(
                            "Closing parenthesis at index " + i
                                    + " has no opening parenthesis"
                    );

                    return reportInvalid(
                            input,
                            "Unmatched closing parenthesis at index " + i
                    );
                }

                balance--;

                logger.info("Matching pair completed at index " + i);
            } else {
                return reportInvalid(
                        input,
                        "Unexpected character '" + current
                                + "' at index " + i
                );
            }
        }

        if (balance > 0) {
            return reportInvalid(
                    input,
                    "Unmatched opening parentheses remaining: " + balance
            );
        }

        logger.info("Validation result for \"" + input + "\": true");
        return true;
    }

    private boolean reportInvalid(String input, String reason) {
        logger.error("Invalid input \"" + input + "\": " + reason);
        logger.info("Validation result for \"" + input + "\": false");

        return false;
    }
}