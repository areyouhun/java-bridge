package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.domain.Moves;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MovesTest {
    
    private static final char COLON = ':';

    @ParameterizedTest
    @CsvSource(value = {"1:U", "0:D"}, delimiter = COLON)
    void 무작위_값이_1이면_U를_0이면_D를_반환한다(int number, String expectedSafeSide) {
        assertThat(Moves.chooseUpOrDown(number)).isEqualTo(expectedSafeSide);
    }

    @ParameterizedTest
    @ValueSource(strings = {"up", "down", "위", "아래", "u", "d"})
    void 입력값이_대문자_U나_D가_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> Moves.getMoveBy(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다음 칸으로 이동하려면 U 또는 D를 입력해야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"U:D"}, delimiter = COLON)
    void Moves_UP은_문자열_U와는_같고_D와는_다른_것으로_간주한다(String up, String down) {
        final Moves moveUp = Moves.UP;
        
        assertThat(moveUp.isCorrectMove(up)).isTrue();
        assertThat(moveUp.isCorrectMove(down)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"D:U"}, delimiter = COLON)
    void Moves_DOWN은_문자열_D와는_같고_U와는_다른_것으로_간주한다(String down, String up) {
        final Moves moveDown = Moves.DOWN;

        assertThat(moveDown.isCorrectMove(down)).isTrue();
        assertThat(moveDown.isCorrectMove(up)).isFalse();
    }
}