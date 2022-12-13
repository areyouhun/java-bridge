package bridge.domain;

import static bridge.util.Constants.BLANK_SPACE;

import bridge.util.Commands;
import bridge.util.Moves;
import java.util.List;

public class DownsideResults extends OneSideResults {

    public DownsideResults() {
        super();
    }

    @Override
    public void update(Moves playerMove, String matchResult) {
        if (playerMove.isUp()) {
            add(BLANK_SPACE);
        }
        if (playerMove.isDown()) {
            add(matchResult);
        }
    }

    private void add(String result) {
        super.getResults().add(result);
    }
}
