import java.util.Scanner;

public class TicTacToe {
    static final int COMPUTER = 1;
    static final int HUMAN = 2;
    static final int SIDE = 3;
    static final char COMPUTERMOVE = 'O';
    static final char HUMANMOVE = 'X';
    static final int MAX_DEPTH = 8; // Increase this value to increase the level of play

    public static void main(String[] args) {
        System.out.println("\t\t\t Tic-Tac-Toe");

        char cont = 'y';
        Scanner scanner = new Scanner(System.in);

        do {
            char choice;
            System.out.print("Do you want to start first? (Y/N): ");
            choice = scanner.next().charAt(0);

            if (choice == 'N')
                playTicTacToe(COMPUTER);
            else if (choice == 'Y')
                playTicTacToe(HUMAN);
            else
                System.out.println("Invalid choice");

            System.out.print("\nDo you want to quit? (Y/N): ");
            cont = scanner.next().charAt(0);
        } while (cont == 'n');
        scanner.close();
    }

    // A function to show the current board status
    static void showBoard(char[][] board) {
        System.out.println("   1   2   3");
        System.out.println("  ┌───┬───┬───┐");
        for (int i = 0; i < SIDE; i++) {
            System.out.print(i + 1 + " │");
            for (int j = 0; j < SIDE; j++) {
                char symbol = board[i][j];
                if (symbol == COMPUTERMOVE)
                    System.out.print(" \u001B[31m" + symbol + "\u001B[0m │"); // Red for Computer's move
                else if (symbol == HUMANMOVE)
                    System.out.print(" \u001B[34m" + symbol + "\u001B[0m │"); // Blue for Human's move
                else
                    System.out.print(" " + symbol + " │");
            }
            System.out.println();
            if (i < SIDE - 1) {
                System.out.println("  ├───┼───┼───┤");
            }
        }
        System.out.println("  └───┴───┴───┘");
    }


    // A function to show the instructions
    static void showInstructions() {
        System.out.println("\nChoose a cell numbered from 1 to 9 as below and play\n");
        System.out.println("\t\t\t 1 | 2 | 3 ");
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t 4 | 5 | 6 ");
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t 7 | 8 | 9 \n");
    }

    // A function to initialise the game
    static void initialise(char[][] board) {
        // Initially the board is empty
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++)
                board[i][j] = ' ';
        }
    }

    // A function to declare the winner of the game
    static void declareWinner(int whoseTurn) {
        if (whoseTurn == COMPUTER)
            System.out.println("COMPUTER has won");
        else
            System.out.println("HUMAN has won");
    }

    // A function that returns true if any of the row
    // is crossed with the same player's move
    static boolean rowCrossed(char[][] board) {
        for (int i = 0; i < SIDE; i++) {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] &&
                    board[i][0] != ' ')
                return true;
        }
        return false;
    }

    // A function that returns true if any of the column
    // is crossed with the same player's move
    static boolean columnCrossed(char[][] board) {
        for (int i = 0; i < SIDE; i++) {
            if (board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i] &&
                    board[0][i] != ' ')
                return true;
        }
        return false;
    }

    // A function that returns true if any of the diagonal
    // is crossed with the same player's move
    static boolean diagonalCrossed(char[][] board) {
        return (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] &&
                board[0][0] != ' ') ||
                (board[0][2] == board[1][1] &&
                        board[1][1] == board[2][0] &&
                        board[0][2] != ' ');
    }

    // A function that returns true if the game is over
    // else it returns a false
    static boolean gameOver(char[][] board) {
        return (rowCrossed(board) || columnCrossed(board) || diagonalCrossed(board));
    }

    // Function to calculate best score
    static int minimax(char[][] board, int depth, boolean isAI) {
        int score = 0;
        int bestScore = 0;
        if (gameOver(board)) {
            if (isAI)
                return -1;
            else
                return 1;
        } else {
            if (depth < 9) {
                if (isAI) {
                    bestScore = -999;
                    for (int i = 0; i < SIDE; i++) {
                        for (int j = 0; j < SIDE; j++) {
                            if (board[i][j] == ' ') {
                                board[i][j] = COMPUTERMOVE;
                                score = minimax(board, depth + 1, false);
                                board[i][j] = ' ';
                                if (score > bestScore) {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                } else {
                    bestScore = 999;
                    for (int i = 0; i < SIDE; i++) {
                        for (int j = 0; j < SIDE; j++) {
                            if (board[i][j] == ' ') {
                                board[i][j] = HUMANMOVE;
                                score = minimax(board, depth + 1, true);
                                board[i][j] = ' ';
                                if (score < bestScore) {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                }
            } else {
                return 0;
            }
        }
    }

    // Function to calculate best move
    static int bestMove(char[][] board, int moveIndex) {
        int x = -1, y = -1;
        int score, bestScore = -999;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = COMPUTERMOVE;
                    score = minimax(board, moveIndex + 1, false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return x * 3 + y;
    }

    // A function to play Tic-Tac-Toe
    static void playTicTacToe(int whoseTurn) {
        char[][] board = new char[SIDE][SIDE];
        int moveIndex = 0, x = 0, y = 0;

        initialise(board);
        showInstructions();

        // Keep playing till the game is over or it is a draw
        while (!gameOver(board) && moveIndex != SIDE * SIDE) {
            int n;
            if (whoseTurn == COMPUTER) {
                n = bestMove(board, moveIndex);
                x = n / SIDE;
                y = n % SIDE;
                board[x][y] = COMPUTERMOVE;
                System.out.printf("COMPUTER has put a %c in cell %d\n\n", COMPUTERMOVE, n + 1);
                showBoard(board);
                moveIndex++;
                whoseTurn = HUMAN;
            } else if (whoseTurn == HUMAN) {
                System.out.print("You can insert in the following positions: ");
                for (int i = 0; i < SIDE; i++)
                    for (int j = 0; j < SIDE; j++)
                        if (board[i][j] == ' ')
                            System.out.print((i * 3 + j) + 1 + " ");
                System.out.println("\nEnter the position: ");
                n = new Scanner(System.in).nextInt();
                n--;
                x = n / SIDE;
                y = n % SIDE;
                if (board[x][y] == ' ' && n < 9 && n >= 0) {
                    board[x][y] = HUMANMOVE;
                    System.out.printf("\nHUMAN has put a %c in cell %d\n\n", HUMANMOVE, n + 1);
                    showBoard(board);
                    moveIndex++;
                    whoseTurn = COMPUTER;
                } else if (board[x][y] != ' ' && n < 9 && n >= 0) {
                    System.out.println("\nPosition is occupied, select any one place from the available places\n\n");
                } else if (n < 0 || n > 8) {
                    System.out.println("Invalid position\n");
                }
            }
        }

        // If the game has drawn
        if (!gameOver(board) && moveIndex == SIDE * SIDE)
            System.out.println("It's a draw");
        else {
            // Toggling the user to declare the actual winner
            if (whoseTurn == COMPUTER)
                whoseTurn = HUMAN;
            else if (whoseTurn == HUMAN)
                whoseTurn = COMPUTER;

            declareWinner(whoseTurn);
           
        }
        
    }
}
