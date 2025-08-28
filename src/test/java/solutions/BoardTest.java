package solutions;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BoardTest {
    @Test
    public void test_case_is_column_full_1 () {
        var board = new Board();
        assertThat(board.isColumnFull(0), is(false));
    }

    @Test
    public void test_case_is_column_full_2 () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {null, blue, null},
            {null, red,  null},
            {null, blue, null}
        };
        var board = new Board(grid);
        assertThat(board.isColumnFull(1), is(true));
    }

}
