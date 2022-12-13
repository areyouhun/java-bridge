package bridge.view;

import static bridge.util.Constants.EMPTY_INPUT;
import static bridge.util.Constants.ERROR_TITLE;
import static bridge.util.Constants.NON_ALPHABETIC_CHARACTER_FOUND;
import static bridge.util.Constants.NON_DIGIT_CHARACTER_FOUND;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String ENTER_BRIDGE_SIZE = "다리의 길이를 입력해주세요.";
    private static final String ENTER_MOVING = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String ENTER_RETRY = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String NON_ALPHABETIC_CHARACTER_INCLUDED = "(.*)[^a-zA-Z](.*)";

    public static int readBridgeSize() {
        final String input = inputBox(ENTER_BRIDGE_SIZE);
        validateBlank(input);
        return toInteger(input);
    }

    public static String readMoving() {
        final String input = inputBox(ENTER_MOVING);
        validateBlank(input);
        validateAlphabet(input);
        return capitalize(input);
    }

    public static String readGameCommand() {
        final String input = inputBox(ENTER_RETRY);
        validateBlank(input);
        validateAlphabet(input);
        return capitalize(input);
    }

    private static String inputBox(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR_TITLE + EMPTY_INPUT);
        }
    }

    private static void validateAlphabet(String input) {
        if (input.matches(NON_ALPHABETIC_CHARACTER_INCLUDED)) {
            throw new IllegalArgumentException(ERROR_TITLE + NON_ALPHABETIC_CHARACTER_FOUND);
        }
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TITLE + NON_DIGIT_CHARACTER_FOUND);
        }
    }

    private static String capitalize(String input) {
        return input.toUpperCase();
    }
}
