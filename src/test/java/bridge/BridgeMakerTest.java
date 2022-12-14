package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeMakerTest {

    private static final char COLON = ':';

    private final BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

    @ParameterizedTest
    @CsvSource(value = {"3:3", "12:12", "15:15", "20:20"}, delimiter = COLON)
    void 입력값에_해당하는_길이의_다리를_생성한다(int inputSize, int expectedSize) {
        final List<String> bridge = bridgeMaker.makeBridge(inputSize);

        assertThat(bridge.size()).isEqualTo(expectedSize);
    }

    @Test
    void 다리_내부는_U_또는_D로_이루어져_있다() {
        final List<String> bridge = bridgeMaker.makeBridge(3);

        assertThat(bridge).containsAnyOf("U", "D");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    void 다리의_길이가_3과_20_사이가_아니면_예외를_발생시킨다(int size) {
        assertThatThrownBy(() -> bridgeMaker.makeBridge(size))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
    }

}