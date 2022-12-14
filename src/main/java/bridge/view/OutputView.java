package bridge.view;

import bridge.domain.BridgeGame;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class OutputView {

    private static final String BLANK_SPACE = " ";
    private static final String ERROR_TITLE = "[ERROR] ";
    private static final String GAME_START_TITLE = "다리 건너기 게임을 시작합니다.\n";

    private static final String BRIDGE_HEAD = "[";
    private static final String BRIDGE_TAIL = "]";
    private static final String BRIDGE_PARTITION = "|";

    private static final String FINAL_RESULT_TITLE = "최종 게임 결과";
    private static final String SUCCESS_OR_FAILURE_TITLE = "게임 성공 여부: ";
    private static final String TRIAL_COUNT_TITLE = "총 시도한 횟수: ";

    public void printGameStart() {
        System.out.println(GAME_START_TITLE);
    }

    public void printMap(BridgeGame bridgeGame) {
        final StringJoiner upsideMap = new StringJoiner(BLANK_SPACE);
        final StringJoiner downsideMap = new StringJoiner(BLANK_SPACE);
        updateMap(upsideMap, bridgeGame.getUpsideResults());
        updateMap(downsideMap, bridgeGame.getDownsideResults());

        printBothSideMaps(upsideMap, downsideMap);
    }

    private void updateMap(StringJoiner oneSideMap, List<String> oneSideResults) {
        oneSideMap.add(BRIDGE_HEAD);
        addOneSideResults(oneSideMap, oneSideResults);
        oneSideMap.add(BRIDGE_TAIL);
    }

    private void addOneSideResults(StringJoiner oneSideMap, List<String> oneSideResults) {
        IntStream.range(0, oneSideResults.size()).forEach(index -> {
            oneSideMap.add(oneSideResults.get(index));
            addPartition(index, oneSideResults, oneSideMap);
        });
    }

    private void addPartition(int index, List<String> oneSideResults, StringJoiner oneSideMap) {
        if (hasMoreThanTwoElements(oneSideResults) && isNotLastIndex(index, oneSideResults)) {
            oneSideMap.add(BRIDGE_PARTITION);
        }
    }

    private boolean hasMoreThanTwoElements(List<String> oneSideResults) {
        return oneSideResults.size() >= 2;
    }

    private boolean isNotLastIndex(int index, List<String> oneSideResults) {
        return index != oneSideResults.size() - 1;
    }

    private void printBothSideMaps(StringJoiner upsideMap, StringJoiner downsideMap) {
        System.out.println(upsideMap);
        System.out.println(downsideMap);
        System.out.println();
    }


    public void printResult(BridgeGame bridgeGame, int trialCount, String gameResult) {
        System.out.println(FINAL_RESULT_TITLE);
        printMap(bridgeGame);
        printSuccessOrFailure(gameResult);
        printTrialCount(trialCount);
    }

    private void printSuccessOrFailure(String gameResult) {
        System.out.println(SUCCESS_OR_FAILURE_TITLE + gameResult);
    }

    private void printTrialCount(int trialCount) {
        System.out.println(TRIAL_COUNT_TITLE + trialCount);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_TITLE + errorMessage);
    }
}
