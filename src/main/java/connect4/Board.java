package connect4;

import java.util.Optional;

import lombok.EqualsAndHashCode;

// Nota: this adds a default and correct equals and hashCode implementation for us
@EqualsAndHashCode
public class Board {
    private static final String SLOT_SEPARATOR = " | ";
    private static final String EMPTY_SLOT = "__";

    private static final int WIN_SIZE = 4;

    private final Token[][] grid;
    private final int rows;
    private final int columns;
    @EqualsAndHashCode.Exclude
    private int lastRow;
    @EqualsAndHashCode.Exclude
    private int lastColumn;

    public Board() {
        // Default size for Connect Four
        this(6, 7);
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Token[rows][columns];
    }

    public Board(Token[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
    }

    public boolean isColumnFull(int column) {
        return this.grid[this.rows-1][column] != null;
    }

    public boolean isFull() {
        for (int column = 0; column < this.columns; column++) {
            if (!isColumnFull(column)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        // The format should be:
        // BL | RE | BL
        // RE | BL | RE
        // BL | RE | BL
        StringBuilder sb = new StringBuilder();
        for (int row = this.rows - 1; row >= 0; row--) {
            for (int col = 0; col < this.columns; col++) {
                if (this.grid[row][col] == null) {
                    sb.append(EMPTY_SLOT);
                } else {
                    sb.append(this.grid[row][col].toString().substring(0, 2));
                }
                if (col < this.columns - 1) {
                    sb.append(SLOT_SEPARATOR);
                }
            }
            if (row > 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void placeToken(int column, Token token) {
        if (isColumnFull(column)) {
            throw new IllegalArgumentException("Column is full, invalid move");
        }
        for (int row = 0; row < this.rows; row++) {
            if (grid[row][column] == null) {
                grid[row][column] = token;
                lastRow = row;
                lastColumn = column;
                break;
            }
        }
    }

    public Optional<Token> getWinner() {
        var lastToken = grid[lastRow][lastColumn];
        // primero verifico si hay un win horizontal
        return checkHorizontalWin(lastToken)
            .or(() -> checkVerticalWin(lastToken));
        // TODO: completar la implementacion considerando los casos de abajo.
        // TODO: la implementacion de los cuatro casos se puede unificar
        // TODO: descomentar los metodos comentados en las pruebas unitarias
        //       para vertificar que las implementaciones son correctas
        // un jugador gana si conecta 4 en una diagonal de pendiente positiva
        // un jugador gana si conecta 4 en una diagonal de pendiente negativa
        // si nunguna de las anteriores se cumple nadie ha ganado
    }

    private Optional<Token> checkHorizontalWin(Token lastToken) {
        int count = 1;
        int colNext = lastColumn + 1;
        int colPrev = lastColumn - 1;
        while (colNext < this.columns && grid[lastRow][colNext] == lastToken) {
            count++;
            colNext++;
        }
        while (colPrev >= 0 && grid[lastRow][colPrev] == lastToken) {
            count++;
            colPrev--;
        }
        if (count >= WIN_SIZE) {
            return Optional.of(lastToken);
        }
        return Optional.empty();
    }

    private Optional<Token> checkVerticalWin(Token lastToken) {
        int countVertical = 1;
        int rowNext = lastRow + 1;
        int rowPrev = lastRow - 1;
        while (rowNext < this.rows && grid[rowNext][lastColumn] == lastToken) {
            countVertical++;
            rowNext++;
        }
        while (rowPrev >= 0 && grid[rowPrev][lastColumn] == lastToken) {
            countVertical++;
            rowPrev--;
        }
        if (countVertical >= WIN_SIZE) {
            return Optional.of(lastToken);
        }
        return Optional.empty();
    }
}
