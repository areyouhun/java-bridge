package bridge;


import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.BridgeGame;
import bridge.domain.Moves;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BridgeGameTest {

    private static final char COLON = ':';

    private final BridgeGame bridgeGame = new BridgeGame();

    @ParameterizedTest
    @CsvSource(value = {"U:O"}, delimiter = COLON)
    void 플레이어가_위로_이동할_때_주어진_답도_위가_맞다면_O를_반환한다(String answerMoveUp,  String MatchResult) {
        assertThat(bridgeGame.move(Moves.UP, answerMoveUp)).isEqualTo(MatchResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"U:D:X"}, delimiter = COLON)
    void 이동할_칸을_건널_수_없으면_X를_반환한다(
            String answerMoveUp, String answerMoveDown, String MatchResult
    ) {
        assertThat(bridgeGame.move(Moves.UP, answerMoveDown)).isEqualTo(MatchResult);
        assertThat(bridgeGame.move(Moves.DOWN, answerMoveUp)).isEqualTo(MatchResult);
    }

    @Test
    void 게임을_다시_시작할_경우_시도_횟수가_1_증가한다() {
        final int previousTrialCount = 1;
        final int currentTrialCount = previousTrialCount + bridgeGame.increaseCount();

        assertThat(currentTrialCount - previousTrialCount).isEqualTo(1);
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