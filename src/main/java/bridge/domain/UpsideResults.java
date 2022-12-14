package bridge.domain;

import static bridge.util.Constants.BLANK_SPACE;

public class UpsideResults extends OneSideResults {

    public UpsideResults() {
        super();
    }

    @Override
    public void update(Moves playerMove, String matchResult) {
        if (playerMove.isUp()) {
            add(matchResult);
        }
        if (playerMove.isDown()) {
            add(BLANK_SPACE);
        }
    }

    private void add(String result) {
        super.getResults().add(result);
    }
}
