package connect4;;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

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

    @Test
    public void test_case_is_full_1 () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, blue, red}
        };
        var board = new Board(grid);
        assertThat(board.isFull(), is(true));
    }

    @Test
    public void test_case_is_full_2 () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, null, red}
        };
        var board = new Board(grid);
        assertThat(board.isFull(), is(false));
    }

    @Test
    public void test_case_to_string () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, null, red}
        };
        var board = new Board(grid);
        var expected = "RE | __ | RE\n" +
                       "BL | RE | BL\n" +
                       "RE | BL | RE";
        assertThat(board.toString(), is(expected));
    }

    @Test
    public void test_case_equals () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid1 = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, null, red}
        };
        var grid2 = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, null, red}
        };
        var board1 = new Board(grid1);
        var board2 = new Board(grid2);
        assertThat(board1, is(equalTo(board2)));
    }

    @Test
    public void test_case_not_equals () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid1 = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, null, red}
        };
        var grid2 = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {blue, null, red}
        };
        var board1 = new Board(grid1);
        var board2 = new Board(grid2);
        assertThat(board1, is(not(equalTo(board2))));
    }

    @Test
    public void test_case_place_token () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, null, red}
        };
        var board = new Board(grid);
        board.placeToken(1, blue);
        var expectedGrid = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, blue, red}
        };
        var expectedBoard = new Board(expectedGrid);
        assertThat(board, is(equalTo(expectedBoard)));
    }

    @Test
    public void test_case_place_token_column_full () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, blue, red},
            {blue, red, blue},
            {red, blue, red}
        };
        var board = new Board(grid);
        try {
            board.placeToken(1, blue);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Column is full, invalid move"));
        }
    }

    @Test
    public void test_case_get_winner_row () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, red, red, null, null, null, null},
            {blue, red, blue, null, null, null, null},
            {red, blue, red, null, null, null, null},
            {blue, red, blue, null, null, null, null},
            {red, blue, red, null, null, null, null},
            {blue, red, blue, null, null, null, null}
        };
        var board = new Board(grid);
        board.placeToken(3, red); // red plays and wins
        var winner = board.getWinner();
        assertThat(winner.isPresent(), is(true));
        assertThat(winner.get().getColor(), is("RED"));
    }

    @Test
    public void test_case_get_winner_column () {
        var blue = new Token("BLUE");
        var red = new Token("RED");
        var grid = new Token[][] {
            {red, blue, blue, null, null, null, null},
            {red, red, blue, null, null, null, null},
            {red, blue, blue, null, null, null, null},
            {null, red, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null}
        };
        var board = new Board(grid);
        board.placeToken(0, red); // red plays and wins
        var winner = board.getWinner();
        assertThat(winner.isPresent(), is(true));
        assertThat(winner.get().getColor(), is("RED"));
    }

    // @Test
    // public void test_case_get_winner_diagonal_ascending () {
    //     var blue = new Token("BLUE");
    //     var red = new Token("RED");
    //     var grid = new Token[][] {
    //         {red, blue, blue, red, null, null, null},
    //         {blue, red, blue, blue, null, null, null},
    //         {blue, blue, red, blue, null, null, null},
    //         {null, null, null, null, null, null, null},
    //         {null, null, null, null, null, null, null},
    //         {null, null, null, null, null, null, null},
    //     };
    //     var board = new Board(grid);
    //     board.placeToken(2, red); // red plays and wins
    //     var winner = board.getWinner();
    //     assertThat(winner.isPresent(), is(true));
    //     assertThat(winner.get().getColor(), is("RED"));
    // }

    // @Test
    // public void test_case_get_winner_diagonal_descending () {
    //     var blue = new Token("BLUE");
    //     var red = new Token("RED");
    //     var grid = new Token[][] {
    //         {red, red, blue, red, null, null, null},
    //         {blue, red, red, red, null, null, null},
    //         {blue, red, blue, blue, null, null, null},
    //         {null, null, null, null, null, null, null},
    //         {null, null, null, null, null, null, null},
    //         {null, null, null, null, null, null, null},
    //     };
    //     var board = new Board(grid);
    //     board.placeToken(0, red); // red plays and wins
    //     var winner = board.getWinner();
    //     assertThat(winner.isPresent(), is(true));
    //     assertThat(winner.get().getColor(), is("RED"));
    // }

    // @Test
    // public void test_case_get_winner_no_winner () {
    //     var blue = new Token("BLUE");
    //     var red = new Token("RED");
    //     var grid = new Token[][] {
    //         {red, blue, blue, red, null, null, null},
    //         {blue, red, blue, blue, null, null, null},
    //         {blue, blue, red, blue, null, null, null},
    //         {null, null, null, null, null, null, null},
    //         {null, null, null, null, null, null, null},
    //         {null, null, null, null, null, null, null},
    //     };
    //     var board = new Board(grid);
    //     board.placeToken(0, red);
    //     var winner = board.getWinner();
    //     assertThat(winner.isPresent(), is(false));
    // }

}
