package bridge.view;

public class InputValidator {

    private static final String NON_ALPHABETIC_CHARACTER_FOUND = "알파벳 외의 값이 입력되었습니다.";
    private static final String EMPTY_INPUT = "입력된 값이 없습니다.";
    private static final String NON_DIGIT_CHARACTER_FOUND = "숫자 외의 값이 입력되었습니다.";
    private static final String NON_ALPHABETIC_CHARACTER_INCLUDED = "(.*)[^a-zA-Z](.*)";

    public int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_DIGIT_CHARACTER_FOUND);
        }
    }

    public String capitalize(String input) {
        validateAlphabet(input);
        return input.toUpperCase();
    }

    public void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }
    }

    private void validateAlphabet(String input) {
        if (input.matches(NON_ALPHABETIC_CHARACTER_INCLUDED)) {
            throw new IllegalArgumentException(NON_ALPHABETIC_CHARACTER_FOUND);
        }
    }
}
