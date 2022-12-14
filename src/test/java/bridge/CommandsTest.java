package bridge;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.domain.Commands;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandsTest {

    @ParameterizedTest
    @ValueSource(strings = {"quit", "r1try", "q", "r", "큐", "!"})
    void 입력값이_대문자_Q나_R이_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> Commands.getCommandBy(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("게임 재개 여부를 정하기 위해선 Q 또는 R을 입력해야 합니다.");
    }

}