package bridge;

import static bridge.util.Constants.ERROR_TITLE;
import static bridge.util.Constants.INVALID_BRIDGE_SIZE;

import bridge.domain.Moves;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BridgeMaker {

    private static final int MINIMUM_BRIDGE_SIZE = 3;
    private static final int MAXIMUM_BRIDGE_SIZE = 20;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        validate(size);
        return Stream.generate(this::pickAnswerMove)
                .limit(size)
                .collect(Collectors.toList());
    }

    private void validate(int size) {
        if (size < MINIMUM_BRIDGE_SIZE || size > MAXIMUM_BRIDGE_SIZE) {
            throw new IllegalArgumentException(ERROR_TITLE + INVALID_BRIDGE_SIZE);
        }
    }

    private String pickAnswerMove() {
        return Moves.chooseUpOrDown(bridgeNumberGenerator.generate());
    }
}
