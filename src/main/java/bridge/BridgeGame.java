package bridge;

import bridge.domain.Moves;

public class BridgeGame {

    private static final int ADDITIONAL_COUNT = 1;
    private static final String SUCCESS = "성공";
    private static final String FAILURE = "실패";
    private static final String CORRECT_MOVE = "O";
    private static final String WRONG_MOVE = "X";


    public BridgeGame() {}

    public String move(Moves playerMove, String answerMove) {
        if (playerMove.isCorrectMove(answerMove)) {
            return CORRECT_MOVE;
        }
        return WRONG_MOVE;
    }

    public int retry(int trialCount) {
        return trialCount + ADDITIONAL_COUNT;
    }

    public String toSuccess() {
        return SUCCESS;
    }

    public String toFailure() {
        return FAILURE;
    }
}
