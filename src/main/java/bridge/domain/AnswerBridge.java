package bridge.domain;

import java.util.List;

public class AnswerBridge {

    private static final int MINIMUM_BRIDGE_SIZE = 3;
    private static final int MAXIMUM_BRIDGE_SIZE = 20;
    private static final String INVALID_BRIDGE_SIZE = "다리 길이는 3부터 20 사이의 숫자여야 합니다.";
    private static final String INVALID_BRIDGE_FORMAT = "다리 내부는 U 또는 D로만 이루어져야 합니다.";

    private final List<String> answerBridge;

    public AnswerBridge(List<String> bridge) {
        validate(bridge);
        this.answerBridge = bridge;
    }

    public boolean isCorrect(Moves playerMove, int index) {
        return playerMove.isCorrectMove(answerBridge.get(index));
    }

    public int getSize() {
        return answerBridge.size();
    }

    private void validate(List<String> bridge) {
        validateSize(bridge);
        validateMoves(bridge);
    }

    private void validateSize(List<String> bridge) {
        if (bridge.size() < MINIMUM_BRIDGE_SIZE || bridge.size() > MAXIMUM_BRIDGE_SIZE) {
            throw new IllegalArgumentException(INVALID_BRIDGE_SIZE);
        }
    }

    private void validateMoves(List<String> bridge) {
        if (!hasMovingUpOrDown(bridge)) {
            throw new IllegalArgumentException(INVALID_BRIDGE_FORMAT);
        }
    }

    private boolean hasMovingUpOrDown(List<String> bridge) {
        return bridge.stream()
                .allMatch(Moves::isUpOrDown);
    }
}
