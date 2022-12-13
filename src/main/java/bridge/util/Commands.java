package bridge.util;

import static bridge.util.Constants.COMMAND_FORMAT;
import static bridge.util.Constants.ERROR_TITLE;

import java.util.Arrays;

public enum Commands {

    RETRY("R"),
    QUIT("Q");

    private final String key;

    Commands(String key) {
        this.key = key;
    }

    public static Commands getCommandBy(String input) {
        return Arrays.stream(Commands.values())
                .filter(command -> command.key.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_TITLE + COMMAND_FORMAT));
    }

    public boolean isKeyForRetrial() {
        return this.equals(RETRY);
    }

    public boolean isKeyForQuit() {
        return this.equals(QUIT);
    }

    public String getKey() {
        return key;
    }
}
