import java.util.Scanner;

class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Fill the board with dashes
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("Board:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col) {
        try {
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                throw new IndexOutOfBoundsException("Move out of bounds!");
            }
            if (board[row][col] != '-') {
                throw new IllegalArgumentException("Cell already taken!");
            }
            board[row][col] = currentPlayer;
            return true;
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) return true;

            if (board[0][i] == currentPlayer &&
                board[1][i] == currentPlayer &&
                board[2][i] == currentPlayer) return true;
        }

        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) return true;

        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) return true;

        return false;
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == '-') return false;
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + ", enter row and column (0-2): ");

            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (game.makeMove(row, col)) {
                    if (game.checkWin()) {
                        game.printBoard();
                        System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                        break;
                    } else if (game.isBoardFull()) {
                        game.printBoard();
                        System.out.println("It's a draw!");
                        break;
                    } else {
                        game.switchPlayer();
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.next(); // Clear the wrong input
            }
        }

        scanner.close();
    }
}
