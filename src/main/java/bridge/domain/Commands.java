package bridge.domain;

import java.util.Arrays;

public enum Commands {

    RETRY("R"),
    QUIT("Q");

    private final String key;

    private static final String COMMAND_FORMAT = "게임 재개 여부를 정하기 위해선 Q 또는 R을 입력해야 합니다.";

    Commands(String key) {
        this.key = key;
    }

    public static Commands getCommandBy(String input) {
        return Arrays.stream(Commands.values())
                .filter(command -> command.key.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_FORMAT));
    }

    public boolean isRetry() {
        return this.equals(RETRY);
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }

    public String getKey() {
        return key;
    }
}
