package bridge;

import static bridge.util.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.BridgeGame;
import bridge.domain.Moves;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BridgeGameTest {

    private BridgeGame bridgeGame;

    @BeforeEach
    void setUp() {
        bridgeGame = new BridgeGame();
    }

    @ParameterizedTest
    @CsvSource(value = {"U:D:O"}, delimiter = COLON)
    void 이동할_칸을_건널_수_있으면_O를_반환한다(
            String answerMoveUp, String answerMoveDown, String MatchResult
    ) {
        assertThat(bridgeGame.move(Moves.UP, answerMoveUp)).isEqualTo(MatchResult);
        assertThat(bridgeGame.move(Moves.DOWN, answerMoveDown)).isEqualTo(MatchResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"U:D:X"}, delimiter = COLON)
    void 이동할_칸을_건널_수_없으면_X를_반환한다(
            String answerMoveUp, String answerMoveDown, String MatchResult
    ) {
        assertThat(bridgeGame.move(Moves.UP, answerMoveDown)).isEqualTo(MatchResult);
        assertThat(bridgeGame.move(Moves.DOWN, answerMoveUp)).isEqualTo(MatchResult);
    }

    @DisplayName("게임을 다시 시작할 경우 시도 횟수가 1 증가한다.")
    @Test
    void increaseTrialCount() {
        final int beforeTrialCount = bridgeGame.getTotalTrialCount();
        bridgeGame.retry();
        final int afterTrialCount = bridgeGame.getTotalTrialCount();

        assertThat(afterTrialCount - beforeTrialCount).isEqualTo(1);
    }

    @DisplayName("게임을 종료할 경우 실패를 반환한다.")
    @Test
    void returnFailure() {
        bridgeGame.quit();
        final String finalResult = bridgeGame.getFinalResult();

        assertThat(finalResult).isEqualTo("실패");
    }
}