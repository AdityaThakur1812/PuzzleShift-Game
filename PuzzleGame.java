import java.util.*;

public class PuzzleGame {
    private static final int SIZE = 4; // 4x4 board
    private static int[][] board = new int[SIZE][SIZE];
    private static int emptyRow = SIZE - 1;
    private static int emptyCol = SIZE - 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        while (true) {
            printBoard();
            System.out.print("Enter move (w/a/s/d) to move up/left/down/right, or 'q' to quit: ");
            char move = scanner.next().charAt(0);

            if (move == 'q') {
                System.out.println("Game Over!");
                break;
            }

            if (makeMove(move)) {
                if (isSolved()) {
                    printBoard();
                    System.out.println("Congratulations! You solved the puzzle.");
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // Initialize the board with numbers 1-15 and an empty space (0)
    private static void initializeBoard() {
        int number = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (number < SIZE * SIZE) {
                    board[i][j] = number++;
                } else {
                    board[i][j] = 0;  // Empty space
                }
            }
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        System.out.println("\nCurrent board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.print("   ");  // Empty space
                } else {
                    System.out.print(board[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }

    // Make the move based on user input
    private static boolean makeMove(char move) {
        int newRow = emptyRow, newCol = emptyCol;

        switch (move) {
            case 'w': newRow--; break;  // Move up
            case 'a': newCol--; break;  // Move left
            case 's': newRow++; break;  // Move down
            case 'd': newCol++; break;  // Move right
            default: return false;
        }

        // Check if the move is valid (within bounds)
        if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
            // Swap the empty space with the adjacent number
            board[emptyRow][emptyCol] = board[newRow][newCol];
            board[newRow][newCol] = 0;
            emptyRow = newRow;
            emptyCol = newCol;
            return true;
        }
        return false;
    }

    // Check if the board is in a solved state
    private static boolean isSolved() {
        int number = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    return board[i][j] == 0;  // The last space should be empty
                }
                if (board[i][j] != number++) {
                    return false;
                }
            }
        }
        return true;
    }
}
