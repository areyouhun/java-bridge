package bridge.domain;

import java.util.List;

public class BridgeGame {

    private static final int ADDITIONAL_COUNT = 1;
    private static final String SUCCESS = "성공";
    private static final String FAILURE = "실패";
    private static final String CORRECT_MOVE = "O";
    private static final String WRONG_MOVE = "X";

    private final OneSideResults upsideResults;
    private final OneSideResults downsideResults;

    private String gameResult;

    public BridgeGame() {
        upsideResults = new UpsideResults();
        downsideResults = new DownsideResults();

        gameResult = toSuccess();
    }

    public String move(Moves playerMove, String answerMove) {
        if (playerMove.isCorrectMove(answerMove)) {
            return CORRECT_MOVE;
        }
        return WRONG_MOVE;
    }

    public void updateBothSideResults(Moves playerMove, String moveResults) {
        upsideResults.update(playerMove, moveResults);
        downsideResults.update(playerMove, moveResults);
    }

    public boolean hasWrongMove() {
        return upsideResults.getResults().contains(WRONG_MOVE) ||
                downsideResults.getResults().contains(WRONG_MOVE);
    }

    public void retry() {
        upsideResults.reset();
        downsideResults.reset();
    }

    public int increaseCount() {
        return ADDITIONAL_COUNT;
    }

    public void quit() {
        gameResult = toFailure();
    }

    public String toSuccess() {
        return SUCCESS;
    }

    public String toFailure() {
        return FAILURE;
    }

    public List<String> getUpsideResults() {
        return upsideResults.getResults();
    }

    public List<String> getDownsideResults() {
        return downsideResults.getResults();
    }

    public String getGameResult() {
        return gameResult;
    }
}
