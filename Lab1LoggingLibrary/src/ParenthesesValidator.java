public class ParenthesesValidator {

    public boolean isValid(String input) {
        if (input == null) {
            return false;
        }

        int balance = 0;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (current == '(') {
                balance++;
            } else if (current == ')') {
                if (balance == 0) {
                    return false;
                }

                balance--;
            } else {
                return false;
            }
        }

        return balance == 0;
    }
}