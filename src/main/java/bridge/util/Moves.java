package bridge.util;

import static bridge.util.Constants.ERROR_TITLE;
import static bridge.util.Constants.MOVING_FORMAT;

import java.util.Arrays;

public enum Moves {

    UP(1, "U"),
    DOWN(0, "D");

    private final int number;
    private final String key;

    Moves(int number, String key) {
        this.number = number;
        this.key = key;
    }

    public static Moves getMoveBy(String input) {
        return Arrays.stream(Moves.values())
                .filter(move -> move.key.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_TITLE + MOVING_FORMAT));
    }

    public static String chooseUpOrDown(int number) {
        if (number == UP.number) {
            return UP.key;
        }
        return DOWN.key;
    }

    public boolean isCorrect(String answerMove) {
        return this.key.equals(answerMove);
    }

    public boolean isUp() {
        return this.equals(UP);
    }

    public boolean isDown() {
        return this.equals(DOWN);
    }
}
