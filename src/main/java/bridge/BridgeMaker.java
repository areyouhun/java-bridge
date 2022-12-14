package bridge;

import bridge.domain.Moves;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        return Stream.generate(this::generateAnswerMove)
                .limit(size)
                .collect(Collectors.toList());
    }

    private String generateAnswerMove() {
        return Moves.chooseUpOrDown(bridgeNumberGenerator.generate());
    }
}
