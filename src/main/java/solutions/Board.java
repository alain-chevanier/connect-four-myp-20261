package solutions;

import java.util.Optional;

public class Board {
    private Token[][] grid;
    private int rows;
    private int columns;
    private int lastRow;
    private int lastColumn;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Token[rows][columns];
    }

    public Board() {
        // Default size for Connect Four
        this(6, 7);
    }

    public Board(Token[][] grid) {
        this.grid = grid;
    }

    public boolean isColumnFull(int column) {
        // O(1)
        return grid[grid.length-1][column] != null;
    }

    public boolean isFull() {
        for (int column = 0; column < columns; column++) {
            if (!isColumnFull(column)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "";
    }

    public void placeToken(int column, Token token) {
        if (isColumnFull(column)) {
            throw new RuntimeException("Column is full, invalid move");
        }
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][column] == null) {
                grid[row][column] = token;
                lastRow = row;
                lastColumn = column;
                break;
            }
        }
    }

    public Optional<Token> getWinner() {
        // un jugador gana si conecta 4 horizontalmente
        for (int row = 0; row < this.rows; row++) {
            // TODO: implement this code
        }
        // un jugador gana si conecta 4 verticalmente
        // un jugador gana si conecta 4 en una diagonal de pendiente positiva
        // un jugador gana si conecta 4 en una diagonal de pendiente negativa
        // si nunguna de las anteriores se cumple nadie ha ganado
        return Optional.empty();
    }
}
