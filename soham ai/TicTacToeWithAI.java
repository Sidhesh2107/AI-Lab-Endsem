import java.util.*;
import java.lang.Math;
public class TicTacToeWithAI {

    public static void clearBoard(char[][] arr){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }
    }

    public static void display(char[][] arr){
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' '){
                    int temp=i*3 + j + 1;
                    System.out.print(" " + temp + " |");
                }
                else
                    System.out.print(" " + arr[i][j] + " |");
            }
            System.out.println();
        }
    }

    public static boolean gameOver(char[][] arr){
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            if(arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2] && arr[i][0] != ' '){
                flag = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i] && arr[0][i] != ' '){
                flag = true;
            }
        }

        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[0][0] != ' '){
            flag = true;
        }

        if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && arr[0][2] != ' '){
            flag=true;
        }
        return flag;
    }

    static int minMax(char[][] arr, int moves, boolean isComputerTurn){

        int curr=0, best=0;
        if (gameOver(arr)){
            if (isComputerTurn)
                return -1; //because if it is computer's turn currently then the previous turn was player's and so player won
            else
                return 1; //this means previous turn was the computer's, so the computer won.
        }
        else {
            if(moves < 9){
                if (isComputerTurn){
                    best = -100;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if(arr[i][j] == ' '){
                                arr[i][j] = 'O';
                                curr = minMax(arr, moves+1, false);
                                arr[i][j] = ' ';
                                best = Math.max(curr, best);
                            }
                        }
                    }
                    return best;
                }
                else {
                    best = 100;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if(arr[i][j] == ' '){
                                arr[i][j] = 'X';
                                curr = minMax(arr, moves+1, true);
                                arr[i][j] = ' ';
                                best = Math.min(curr, best);
                            }
                        }
                    }
                    return best;
                }
            }
            else {
                return 0;
            }
        }

    }

    static int nextMove(char[][] arr, int moves){
        int max=-999, x=0, y=0, curr=0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' '){
                    arr[i][j] = 'O';
                    curr = minMax(arr, moves+1, false);
                    arr[i][j] = ' ';
                    if (curr>max){
                        max = curr;
                        x=i;
                        y=j;
                    }
                }
            }
        }
        return x*3 + y;
    }

    public static void play(boolean isHumanTurn){
        Scanner scan = new Scanner(System.in);
        char[][] arr = new char[3][3];
        int moves=0, x=0, y=0;

        clearBoard(arr);
        display(arr);
        while (!gameOver(arr) && moves<9){
            if (isHumanTurn){
                System.out.println("Please Enter a number from 1-9");
                int n = scan.nextInt();
                n--;
                x = n/3;
                y = n%3;
                if (n<9 && n>=0 && arr[x][y] == ' '){
                    arr[x][y] = 'X';
                    moves++;
                    isHumanTurn = false;
                }
                else{
                    System.out.println("Invalid position please enter correct position");
                }

            }
            else{
                int pos = nextMove(arr, moves);
                x = pos/3;
                y = pos%3;
                arr[x][y] = 'O';
                System.out.println("The state after computer's move at "+pos);
                display(arr);
                moves++;
                isHumanTurn=true;
            }
        }
        if (!gameOver(arr) && moves == 9){
            System.out.println("The game ends in a draw");
        }
        else {
            if (isHumanTurn)
                System.out.println("Computer has won!");//because if its the player's turn that means the game ended with computer's move making it the winner
            else
                System.out.println("The Player has won!");

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to tic tac toe with AI");
        play(true);
    }
}
