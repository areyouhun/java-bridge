package bridge;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.domain.AnswerBridge;
import java.util.List;
import org.junit.jupiter.api.Test;

class AnswerBridgeTest {

    private void sizeOutOfRange(List<String> bridge) {
        assertThatThrownBy(() -> new AnswerBridge(bridge))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다리 길이는 3부터 20 사이의 숫자여야 합니다.");
    }

    @Test
    void 다리_생성_시_매개값에_U_또는_D_이외의_값이_들어_있으면_예외를_발생시킨다() {
        final List<String> bridge = List.of("U", "D", "B");

        assertThatThrownBy(() -> new AnswerBridge(bridge))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다리 내부는 U 또는 D로만 이루어져야 합니다.");
    }

    @Test
    void 다리의_길이가_3과_20_사이가_아니면_예외를_발생시킨다() {
        final List<String> bridgeSizeTwo = List.of("U", "D");
        final List<String> bridgeSizeTwentyOne = List.of(
                "U", "D", "U", "D", "U", "D", "U", "D", "U",
                "D", "U", "D", "U", "D", "U", "D", "U", "D", "U", "D", "U"
        );
        sizeOutOfRange(bridgeSizeTwo);
        sizeOutOfRange(bridgeSizeTwentyOne);
    }
}