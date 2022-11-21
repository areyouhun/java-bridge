package bridge.util;

import static bridge.util.Constants.*;

import bridge.util.CommandKeys;
import java.util.Arrays;

public class InputValidator {
    private static final String NO_SPACE = "";
    private static final String NON_DIGIT_CHARACTER = "[^0-9]";
    private static final String NON_ALPHABETIC_CHARACTER = "[^a-zA-Z]";

    public static void bridgeSize(String input) {
        validateBlank(input);
        validateNumericType(input);
        validateRange(Integer.parseInt(input));
    }

    private static void validateBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TITLE + EMPTY_INPUT);
        }
    }

    private static void validateNumericType(String input) {
        if (isNotNumeric(input)) {
            throw new IllegalArgumentException(ERROR_TITLE + NON_DIGIT_CHARACTER_FOUND);
        }
    }

    private static boolean isNotNumeric(String input) {
        final String[] splitted = input.split(NO_SPACE);

        return Arrays.stream(splitted)
                .anyMatch(element ->
                        element.matches(NON_DIGIT_CHARACTER)
                );
    }

    private static void validateRange(int size) {
        if (size < MINIMUM_BRIDGE_SIZE || size > MAXIMUM_BRIDGE_SIZE) {
            throw new IllegalArgumentException(ERROR_TITLE + BRIDGE_SIZE_FORMAT);
        }
    }

    public static void moving(String input) {
        validateBlank(input);
        validateAlphabeticType(input);
        if (!CommandKeys.isUp(input.toUpperCase()) && !CommandKeys.isDown(input.toUpperCase())) {
            throw new IllegalArgumentException(ERROR_TITLE + MOVING_FORMAT);
        }
    }

    public static void gameCommand(String input) {
        validateBlank(input);
        validateAlphabeticType(input);
        if (!CommandKeys.isRetry(input.toUpperCase()) && !CommandKeys.isQuit(input.toUpperCase())) {
            throw new IllegalArgumentException(ERROR_TITLE + COMMAND_FORMAT);
        }
    }

    private static void validateAlphabeticType(String input) {
        if (isNotAlphabetic(input)) {
            throw new IllegalArgumentException(ERROR_TITLE + NON_ALPHABETIC_CHARACTER_FOUND);
        }
    }

    private static boolean isNotAlphabetic(String input) {
        final String[] splitted = input.split(NO_SPACE);

        return Arrays.stream(splitted)
                .anyMatch(element ->
                        element.matches(NON_ALPHABETIC_CHARACTER)
                );
    }
}
