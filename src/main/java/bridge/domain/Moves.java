package bridge.domain;

public enum Moves {

    UP(1, "U"),
    DOWN(0, "D");

    private final int number;
    private final String key;

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
}
