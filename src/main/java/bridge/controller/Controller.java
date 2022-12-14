package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
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

    private final InputView inputView;
    private final OutputView outputView;

    private List<String> answerBridge;
    private BridgeGame bridgeGame;
    
    private int trialCount;

    public Controller() {
        inputView = new InputView();
        outputView = new OutputView();

        answerBridge = repeat(this::toAnswerBridge);
        bridgeGame = new BridgeGame();
        
        trialCount = INITIAL_COUNT;
    }

    public void start() {
        for (int index = 0; index < answerBridge.size(); index++) {
            final Moves playerMove = repeat(this::toPlayerMove);
            compareMoves(playerMove, answerBridge.get(index));
            index = changeIndexIfResultsHaveWrongMove(index);
        }
    }

    private void compareMoves(Moves playerMove, String answerMove) {
        final String moveResult = bridgeGame.move(playerMove, answerMove);
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
            bridgeGame.quit();
        }
    }

    private int moveToFirstOrLastIndex(int index, Commands playerCommand) {
        if (playerCommand.isRetry()) {
            index = toFirstIndex();
        }
        if (playerCommand.isQuit()) {
            index = toLastIndex();
        }
        return index;
    }

    private List<String> toAnswerBridge() {
        final BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(inputView.readBridgeSize());
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
        return answerBridge.size() - 1;
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
