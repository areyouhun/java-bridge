package bridge.domain;

public class UpsideResults extends OneSideResults{

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
