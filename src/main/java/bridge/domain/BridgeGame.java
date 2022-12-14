package bridge.domain;

import java.util.List;

public class BridgeGame {

    private static final int ADDITIONAL_COUNT = 1;
    private static final String SUCCESS = "성공";
    private static final String FAILURE = "실패";
    private static final String CORRECT_MOVE = "O";
    private static final String WRONG_MOVE = "X";

    final OneSideResults upsideResults;
    final OneSideResults downsideResults;

    public BridgeGame() {
        upsideResults = new UpsideResults();
        downsideResults = new DownsideResults();
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

    public void retry() {
        upsideResults.reset();
        downsideResults.reset();
    }

    public int increaseCount(int trialCount) {
        return trialCount + ADDITIONAL_COUNT;
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
}
