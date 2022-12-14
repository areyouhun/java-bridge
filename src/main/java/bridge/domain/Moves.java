package bridge.domain;

import java.util.Arrays;

public enum Moves {

    UP(1, "U"),
    DOWN(0, "D");

    private final int number;
    private final String key;

    private static final String MOVING_FORMAT = "다음 칸으로 이동하기 위해선 U 또는 D를 입력해야 합니다.";

    Moves(int number, String key) {
        this.number = number;
        this.key = key;
    }

    public static String chooseUpOrDown(int number) {
        if (number == UP.number) {
            return UP.key;
        }
        return DOWN.key;
    }

    public static Moves getMoveBy(String input) {
        return Arrays.stream(values())
                .filter(move -> move.key.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MOVING_FORMAT));
    }

    public boolean isCorrectMove(String answerMove) {
        return this.key.equals(answerMove);
    }

    public boolean isUp() {
        return this.equals(UP);
    }

    public boolean isDown() {
        return this.equals(DOWN);
    }
}
