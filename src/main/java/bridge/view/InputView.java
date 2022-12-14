package bridge.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String ENTER_BRIDGE_SIZE = "다리의 길이를 입력해주세요.";
    private static final String ENTER_MOVING = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String ENTER_RETRY = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    private final InputValidator inputValidator;

    public InputView() {
        inputValidator = new InputValidator();
    }

    public int readBridgeSize() {
        final String input = inputBox(ENTER_BRIDGE_SIZE);
        inputValidator.validateBlank(input);
        return inputValidator.toInteger(input);
    }

    public String readMoving() {
        final String input = inputBox(ENTER_MOVING);
        inputValidator.validateBlank(input);
        return inputValidator.capitalize(input);
    }

    public String readGameCommand() {
        final String input = inputBox(ENTER_RETRY);
        inputValidator.validateBlank(input);
        return inputValidator.capitalize(input);
    }

    private String inputBox(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
