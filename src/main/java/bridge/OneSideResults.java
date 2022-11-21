package bridge;

import bridge.util.CommandKeys;
import java.util.ArrayList;
import java.util.List;

public abstract class OneSideResults {
    protected final List<String> results;

    public OneSideResults() {
        results = new ArrayList<>();
    }

    public List<String> getResults() {
        return results;
    }

    public abstract void update(String playerMove, String matchResult);

    public void reset(String input) {
        if (CommandKeys.isRetry(input)) {
            initialize();
        }
    }

    private void initialize() {
        results.clear();
    }
}
