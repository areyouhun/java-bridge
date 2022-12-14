package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.AnswerBridge;
import bridge.domain.BridgeGame;
import bridge.domain.Commands;
import bridge.domain.Moves;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private static final int INITIAL_COUNT = 1;
    private static final int INITIAL_INDEX = -1;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BridgeGame bridgeGame;

    private String gameResult;
    private int trialCount;

    public Controller() {
        outputView.printGameStart();
        bridgeGame = new BridgeGame(repeat(this::toAnswerBridge));
        gameResult = bridgeGame.toSuccess();
        trialCount = INITIAL_COUNT;
    }

    public void start() {
        for (int index = 0; index < bridgeGame.getAnswerBridgeSize(); index++) {
            final Moves playerMove = repeat(this::toPlayerMove);
            updateMoveResult(playerMove, index);
            outputView.printMap(bridgeGame);
            index = changeIndexIfResultsHaveWrongMove(index);
        }
        outputView.printResult(bridgeGame, trialCount, gameResult);
    }

    private void updateMoveResult(Moves playerMove, int index) {
        final String moveResult = bridgeGame.move(playerMove, index);
        bridgeGame.updateBothSideResults(playerMove, moveResult);
    }

    private int changeIndexIfResultsHaveWrongMove(int index) {
        if (bridgeGame.hasWrongMove()) {
            final Commands playerCommand = repeat(this::toPlayerCommand);
            checkIfRetryOrQuit(playerCommand);
            index = moveToFirstOrLastIndex(index, playerCommand);
        }
        return index;
    }

    private void checkIfRetryOrQuit(Commands playerCommand) {
        if (playerCommand.isRetry()) {
            bridgeGame.retry();
            trialCount += bridgeGame.increaseCount();
        }
        if (playerCommand.isQuit()) {
            gameResult = bridgeGame.toFailure();
        }
    }

    private int moveToFirstOrLastIndex(int index, Commands playerCommand) {
        if (playerCommand.isRetry()) {
            return toFirstIndex();
        }
        if (playerCommand.isQuit()) {
            return toLastIndex();
        }
        return index;
    }

    private AnswerBridge toAnswerBridge() {
        final BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        final List<String> answerBridge = bridgeMaker.makeBridge(inputView.readBridgeSize());
        return new AnswerBridge(answerBridge);
    }

    private Moves toPlayerMove() {
        return Moves.getMoveBy(inputView.readMoving());
    }

    private Commands toPlayerCommand() {
        return Commands.getCommandBy(inputView.readGameCommand());
    }

    private int toFirstIndex() {
        return INITIAL_INDEX; // For statement will increase this index to 0.
    }

    private int toLastIndex() {
        return bridgeGame.getAnswerBridgeSize() - 1;
    }

    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return repeat(inputReader);
        }
    }
}
