package bridge.domain;

public class BridgeGame {

    private static final int INITIAL_COUNT = 1;
    private static final String SUCCESS = "성공";
    private static final String FAILURE = "실패";
    private static final String CORRECT_MOVE = "O";
    private static final String WRONG_MOVE = "X";

    private final OneSideResults upsideResults;
    private final OneSideResults downsideResults;
    
    private int totalTrialCount;
    private String finalResult;

    public BridgeGame() {
        upsideResults = new UpsideResults();
        downsideResults = new DownsideResults();
        
        totalTrialCount = INITIAL_COUNT;
        finalResult = SUCCESS;
    }

    public String move(Moves playerMove, String answerMove) {
        if (playerMove.isCorrect(answerMove)) {
            return CORRECT_MOVE;
        }
        return WRONG_MOVE;
    }

    public void updateBothSidesResults(Moves playerMove, String moveResult) {
        upsideResults.update(playerMove, moveResult);
        downsideResults.update(playerMove, moveResult);
    }

    public void retry() {
        resetBothSidesResults();
        totalTrialCount++;
    }

    private void resetBothSidesResults() {
        upsideResults.reset();
        downsideResults.reset();
    }

    public void quit() {
        finalResult = FAILURE;
    }

    public boolean hasWrongMove() {
        return upsideResults.getResults().contains(WRONG_MOVE) ||
                downsideResults.getResults().contains(WRONG_MOVE);
    }

    public OneSideResults getUpsideResults() {
        return upsideResults;
    }

    public OneSideResults getDownsideResults() {
        return downsideResults;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public int getTotalTrialCount() {
        return totalTrialCount;
    }
}
