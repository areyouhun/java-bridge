package bridge;

import bridge.domain.Moves;

public class BridgeGame {

    private static final String CORRECT_MOVE = "O";
    private static final String WRONG_MOVE = "X";

    public BridgeGame() {}

    public String move(Moves playerMove, String answerMove) {
        if (playerMove.isCorrectMove(answerMove)) {
            return CORRECT_MOVE;
        }
        return WRONG_MOVE;
    }

    public void retry() {
    }
}
