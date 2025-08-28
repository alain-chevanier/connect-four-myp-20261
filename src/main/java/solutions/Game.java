package solutions;

/**
 * Game
 */
public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private int winSize;

    public Game(int rows, int columns, int winSize, Player[] players) {
        this.board = new Board(rows, columns);
        this.players = players;
        this.currentPlayerIndex = 0;
        this.winSize = winSize;
    }

    public void start() {}
    public boolean placeToken(int column) {
        return false;
    }

    public void showBoard() {
        // Display the board
    }
}
