package bridge;

import static bridge.util.Constants.COLON;
import static bridge.util.Constants.ERROR_TITLE;
import static bridge.util.Constants.MOVING_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.domain.Moves;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MovesTest {

    @ParameterizedTest
    @CsvSource(value = {"1:U", "0:D"}, delimiter = COLON)
    void 무작위값이_1이면_U를_0이면_D를_반환한다(int number, String expectedSafeSide) {
        assertThat(Moves.chooseUpOrDown(number)).isEqualTo(expectedSafeSide);
    }

    @ParameterizedTest
    @ValueSource(strings = {"up", "down", "위", "아래", "u", "d"})
    void 입력값이_대문자_U나_D가_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> Moves.getMoveBy(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_TITLE + MOVING_FORMAT);
    }
}