package spw4.connectfour;

public class ConnectFourImpl implements ConnectFour {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;

    private Player[][] gameBoard;
    private Player currentPlayer;
    private Player winner = Player.none;

    public ConnectFourImpl(Player playerOnTurn) {
        initConnectFour(playerOnTurn);
    }

    private void initConnectFour(Player playerOnTurn) {
        this.currentPlayer = playerOnTurn;
        gameBoard = new Player[ROWS][COLUMNS];

        for (int row = 0; row < ROWS; row++)
            for (int col = 0; col < COLUMNS; col++)
                gameBoard[row][col] = Player.none;
    }

    public Player getPlayerAt(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) throw new IndexOutOfBoundsException();
        return gameBoard[row][col];
    }

    public Player getPlayerOnTurn() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        if(winner != Player.none) return true;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (gameBoard[i][j] != Player.none) {
                    if (checkDirection(i, j, 1, 0) || // horizontal
                            checkDirection(i, j, 0, 1) || // vertical
                            checkDirection(i, j, 1, 1) || // diagonal down-right
                            checkDirection(i, j, 1, -1)) { // diagonal up-right
                        winner = gameBoard[i][j];
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkDirection(int i, int j, int i1, int i2) {
        int count = 0;
        for (int k = 0; k < 4; k++) {
            int r = i + k * i1;
            int c = j + k * i2;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && gameBoard[r][c] == gameBoard[i][j]) count++;
            else break;
        }
        return count == 4;
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ");
        sb.append(currentPlayer == Player.red ? "RED" : "YELLOW");
        sb.append('\n');
        for (int i = 0; i < ROWS; i++) {
            sb.append("| ");
            for (int j = 0; j < COLUMNS; j++)
                sb.append(gameBoard[i][j].toString()).append("  ");
            sb.append("|\n");
        }
        return sb.toString();
    }

    public void drop(int col) {
        if(winner != Player.none) return;
        for (int i = ROWS - 1; i >= 0; i--) {
            if (gameBoard[i][col] == Player.none) {
                gameBoard[i][col] = currentPlayer;
                swapPlayer();
                return;
            }
        }

        System.out.println("Row Full - Try again");
    }

    public void reset(Player playerOnTurn) {
        initConnectFour(playerOnTurn);
    }

    private void swapPlayer() {
        currentPlayer = currentPlayer == Player.red ? Player.yellow : Player.red;
    }
}
