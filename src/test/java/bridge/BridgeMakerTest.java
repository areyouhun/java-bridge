package bridge;

import static bridge.util.Constants.COLON;
import static bridge.util.Constants.ERROR_TITLE;
import static bridge.util.Constants.INVALID_BRIDGE_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeMakerTest {
    private BridgeMaker bridgeMaker;

    @BeforeEach
    void setUp() {
        bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    @ParameterizedTest
    @CsvSource(value = {"3:3", "12:12", "15:15", "20:20"}, delimiter = COLON)
    void 입력값에_해당하는_길이의_다리를_생성한다(int inputSize, int expectedSize) {
        final List<String> bridge = bridgeMaker.makeBridge(inputSize);

        assertThat(bridge.size()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    void 다리의_길이가_3과_20_사이가_아니면_예외를_발생시킨다(int size) {
        assertThatThrownBy(() -> bridgeMaker.makeBridge(size))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_TITLE + INVALID_BRIDGE_SIZE);
    }
}