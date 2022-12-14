package bridge.view;

public class OutputView {

    private static final String ERROR_TITLE = "[ERROR] ";
    private static final String GAME_START_TITLE = "다리 건너기 게임을 시작합니다.\n";

    public static void printGameStart() {
        System.out.println(GAME_START_TITLE);
    }

    public void printMap() {
    }

    public void printResult() {
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_TITLE + errorMessage);
    }
}
