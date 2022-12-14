package bridge;

import static bridge.util.Constants.COMMAND_FORMAT;
import static bridge.util.Constants.ERROR_TITLE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.domain.Commands;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandsTest {

    @ParameterizedTest
    @ValueSource(strings = {"quit", "retry", "종료", "재개", "q", "r"})
    void 입력값이_대문자_Q나_R이_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> Commands.getCommandBy(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_TITLE + COMMAND_FORMAT);
    }
}