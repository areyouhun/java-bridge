package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Commands;
import bridge.domain.Moves;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private static final int INITIAL_INDEX = -1;

    private final List<String> answerBridge;
    private final BridgeGame bridgeGame;

    public Controller() {
        OutputView.printGameStart();
        answerBridge = repeat(this::toAnswerBridge);
        bridgeGame = new BridgeGame();
    }

    private List<String> toAnswerBridge() {
        final BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(InputView.readBridgeSize());
    }

    public void start() {
        for (int index = 0; index < answerBridge.size(); index++) {
            final Moves playerMove = repeat(this::toPlayerMove);
            compareMoves(playerMove, answerBridge.get(index));
            OutputView.printMap(bridgeGame);
            index = changeIndexIfResultsHaveWrongMove(index);
        }
        OutputView.printResult(bridgeGame);
    }
    
    private void compareMoves(Moves playerMove, String answerMove) {
        final String moveResult = bridgeGame.move(playerMove, answerMove);
        bridgeGame.updateBothSidesResults(playerMove, moveResult);
    }

    private Moves toPlayerMove() {
        return Moves.getMoveBy(InputView.readMoving());
    }

    private int changeIndexIfResultsHaveWrongMove(int index) {
        if (bridgeGame.hasWrongMove()) {
            final Commands playerCommand = repeat(this::toPlayerCommand);
            checkIfRetryOrNot(playerCommand);
            index = moveToFirstOrLastIndex(index, playerCommand);
        }
        return index;
    }

    private Commands toPlayerCommand() {
        return Commands.getCommandBy(InputView.readGameCommand());
    }

    private void checkIfRetryOrNot(Commands playerCommand) {
        if (playerCommand.isRetry()) {
            bridgeGame.retry();
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
            OutputView.printErrorMessage(e.getMessage());
            return repeat(inputReader);
        }
    }
}
