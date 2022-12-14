package bridge.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 입력값이_비어_있거나_스페이스만_있다면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> inputValidator.validateBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력된 값이 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"one", "!", "12three", "삼"})
    void int_타입으로_변환_시_입력값에_숫자_외의_값이_들어_있다면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> inputValidator.toInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 외의 값이 입력되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"@", "ㅁb", "에이치"})
    void 대문자로_변환_시_입력값에_알파벳_외의_값이_들어_있다면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> inputValidator.capitalize(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("알파벳 외의 값이 입력되었습니다.");
    }
}