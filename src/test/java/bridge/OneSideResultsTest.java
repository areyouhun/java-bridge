package bridge;


import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.DownsideResults;
import bridge.domain.Moves;
import bridge.domain.OneSideResults;
import bridge.domain.UpsideResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneSideResultsTest {

    private static final int FIRST_INDEX = 0;

    private OneSideResults upsideResults;
    private OneSideResults downsideResults;

    private void updateBothSidesResults(Moves playerMove, String moveResult) {
        upsideResults.update(playerMove, moveResult);
        downsideResults.update(playerMove, moveResult);
    }
    
    private void resetBothSideResults() {
        upsideResults.reset();
        downsideResults.reset();
    }

    private void areUpdatedAsExpected(String expectedUpsideResult, String expectedDownsideResult) {
        assertThat(upsideResults.getResults().get(FIRST_INDEX))
                .isEqualTo(expectedUpsideResult);
        assertThat(downsideResults.getResults().get(FIRST_INDEX))
                .isEqualTo(expectedDownsideResult);
    }
    
    private void areResetAsExpected(int SizeOfUpsideResults, int SizeOfDownsideResults) {
        assertThat(SizeOfUpsideResults).isEqualTo(0);
        assertThat(SizeOfDownsideResults).isEqualTo(0);
    }
    
    @BeforeEach
    void setUp() {
        upsideResults = new UpsideResults();
        downsideResults = new DownsideResults();
    }

    @Test
    void 플레이어가_위_칸을_건넜을_때_통과했다면_위_칸에_O가_추가되고_아래_칸에는_공백이_추가된다() {
        updateBothSidesResults(Moves.UP, "O");

        areUpdatedAsExpected("O", " ");
    }

    @Test
    void 플레이어가_아래_칸을_건넜을_때_통과했다면_위_칸에_공백이_추가되고_아래_칸에는_O가_추가된다() {
        updateBothSidesResults(Moves.DOWN, "O");

        areUpdatedAsExpected(" ", "O");
    }

    @Test
    void 플레이어가_위_칸을_건넜을_때_실패했다면_위_칸에_X가_추가되고_아래_칸에는_공백이_추가된다() {
        updateBothSidesResults(Moves.UP, "X");

        areUpdatedAsExpected("X", " ");
    }

    @Test
    void 플레이어가_아래_칸을_건넜을_때_실패했다면_위_칸에_공백이_추가되고_아래_칸에는_X가_추가된다() {
        updateBothSidesResults(Moves.DOWN, "X");

        areUpdatedAsExpected(" ", "X");
    }

    @Test
    void 플레이어가_게임을_다시_시작하는_경우_양쪽_칸의_이동_결과_기록이_전부_사라진다() {
        updateBothSidesResults(Moves.UP, "O");
        updateBothSidesResults(Moves.DOWN, "X");
        resetBothSideResults();
        final int currentSizeOfUpsideResults = upsideResults.getResults().size();
        final int currentSizeOfDownsideResults = downsideResults.getResults().size();

        areResetAsExpected(currentSizeOfUpsideResults, currentSizeOfDownsideResults);
    }
}