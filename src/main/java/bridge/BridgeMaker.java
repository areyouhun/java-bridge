package bridge;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BridgeMaker {

    private static final int MINIMUM_BRIDGE_SIZE = 3;
    private static final int MAXIMUM_BRIDGE_SIZE = 20;
    private static final String INVALID_BRIDGE_SIZE = "다리 길이는 3부터 20 사이의 숫자여야 합니다.";

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        validate(size);
        return Stream.generate(this::generateAnswerMove)
                .limit(size)
                .collect(Collectors.toList());
    }

    private void validate(int size) {
        if (size < MINIMUM_BRIDGE_SIZE || size > MAXIMUM_BRIDGE_SIZE) {
            throw new IllegalArgumentException(INVALID_BRIDGE_SIZE);
        }
    }

    private String generateAnswerMove() {
        final int number = bridgeNumberGenerator.generate();
        if (number == 0) {
            return "D";
        }
        return "U";
    }
}
