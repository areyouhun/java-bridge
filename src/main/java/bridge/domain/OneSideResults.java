package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class OneSideResults {

    private final List<String> results;

    public OneSideResults() {
        this.results = new ArrayList<>();
    }

    public abstract void update(Moves playerMove, String matchResult);

    public void reset() {
        results.clear();
    };

    public List<String> getResults() {
        return results;
    }
}
