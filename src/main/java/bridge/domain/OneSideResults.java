package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class OneSideResults {

    protected static final String BLANK_SPACE = " ";

    private final List<String> results;

    public OneSideResults() {
        this.results = new ArrayList<>();
    }

    public abstract void update(Moves playerMove, String moveResult);

    public boolean contains(String input) {
        return results.contains(input);
    }

    public void reset() {
        results.clear();
    }

    public List<String> getResults() {
        return results;
    }
}
