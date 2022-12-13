package bridge.domain;

import static bridge.util.Constants.BLANK_SPACE;

import bridge.util.Commands;

public class DownsideResults extends OneSideResults {

    public DownsideResults() {
        super();
    }

    @Override
    public void update(String playerMove, String matchResult) {
        if (Commands.isUp(playerMove)) {
            addResult(BLANK_SPACE);
        }
        if (Commands.isDown(playerMove)) {
            addResult(matchResult);
        }
    }

    private void addResult(String result) {
        super.getResults()
                .add(result);
    }
}
