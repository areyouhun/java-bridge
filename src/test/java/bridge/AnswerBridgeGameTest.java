package bridge;


import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.AnswerBridge;
import bridge.domain.BridgeGame;
import bridge.domain.Moves;
import java.util.List;
import org.junit.jupiter.api.Test;

class AnswerBridgeGameTest {

    private static final int FIRST_ANSWER_MOVE_INDEX = 0;
    private static final int SECOND_ANSWER_MOVE_INDEX = 1;

    private final AnswerBridge answerBridge = new AnswerBridge(List.of("U", "D", "D"));
    private final BridgeGame bridgeGame = new BridgeGame(answerBridge);

    @Test
    void 플레이어의_이동_방향과_답의_이동_방향이_일치한다면_O를_반환한다() {
        final Moves playerMove = Moves.UP;

        assertThat(bridgeGame.move(playerMove, FIRST_ANSWER_MOVE_INDEX)).isEqualTo("O");
    }

    @Test
    void 플레이어의_이동_방향과_답의_이동_방향이_일치하지_않는다면_X를_반환한다() {
        final Moves playerMove = Moves.UP;

        assertThat(bridgeGame.move(playerMove, SECOND_ANSWER_MOVE_INDEX)).isEqualTo("X");
    }

    @Test
    void 게임을_다시_시작할_경우_시도_횟수가_1_증가한다() {
        final int previousTrialCount = 1;
        final int currentTrialCount = previousTrialCount + bridgeGame.increaseCount();

        assertThat(currentTrialCount).isEqualTo(2);
    }

    @Test
    void toSuccess_메서드는_문자열_성공을_반환한다() {
        assertThat(bridgeGame.toSuccess()).isEqualTo("성공");
    }

    @Test
    void toFailure_메서드는_문자열_실패를_반환한다() {
        assertThat(bridgeGame.toFailure()).isEqualTo("실패");
    }
}