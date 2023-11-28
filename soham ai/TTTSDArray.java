import java.util.*;
import java.lang.Math;
public class TTTSDArray {

    public static void initBoard(char[] arr){
        for (int i = 0; i < 9; i++) {
                arr[i] = ' ';
        }
    }

    public static void display(char[] arr){
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print(" | ");
            if(arr[i] == ' '){
                System.out.print(i);
            }
            else {
                System.out.print(arr[i]);
            }

            if(i == 2 || i==5 || i==8) {
                System.out.print(" |");
                System.out.println();
            }
        }
    }

    public static boolean gameOver(char[] arr){
        boolean flag = false;
        for (int i = 0; i <= 6; i=i+3) {
            if(arr[i] == arr[i+1] && arr[i+1] == arr[i+2] && arr[i] != ' '){
                flag = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(arr[i] == arr[i+3] && arr[i+3] == arr[i+6] && arr[i] != ' '){
                flag = true;
            }
        }

        if (arr[0] == arr[4] && arr[4] == arr[8] && arr[0] != ' '){
            flag = true;
        }

        if (arr[2] == arr[4] && arr[4] == arr[6] && arr[2] != ' '){
            flag=true;
        }
        return flag;
    }

    static int minMax(char[] arr, int moves, boolean isComputerTurn){

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
                    for (int i = 0; i < 9; i++) {
                            if(arr[i] == ' '){
                                arr[i] = 'O';
                                curr = minMax(arr, moves+1, false);
                                arr[i] = ' ';
                                best = Math.max(curr, best);
                        }
                    }
                    return best;
                }
                else {
                    best = 100;
                    for (int i = 0; i < 9; i++) {
                        if(arr[i] == ' '){
                            arr[i] = 'X';
                            curr = minMax(arr, moves+1, true);
                            arr[i] = ' ';
                            best = Math.min(curr, best);
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

    static int nextMove(char[] arr, int moves){
        int max=-999, x=0, curr=0;

        for (int i = 0; i < 9; i++) {
            if (arr[i] == ' '){
                arr[i] = 'O';
                curr = minMax(arr, moves+1, false);
                arr[i] = ' ';
                if (curr>max){
                    max = curr;
                    x=i;
                }
            }
        }
        return x;
    }

    public static void play(boolean isHumanTurn){
        Scanner scan = new Scanner(System.in);
        char[] arr = new char[9];
        int moves=0;

        initBoard(arr);
        display(arr);
        while (!gameOver(arr) && moves<9){
            if (isHumanTurn){
                System.out.println("Please Enter a number from 1-9");
                int n = scan.nextInt();
                if (n<9 && n>=0 && arr[n] == ' '){
                    arr[n] = 'X';
                    moves++;
                    isHumanTurn = false;
                }
                else{
                    System.out.println("Invalid position please enter correct position");
                }

            }
            else{
                int pos = nextMove(arr, moves);
                arr[pos] = 'O';
                System.out.println("The state after computer's move at "+pos);
                display(arr);
                moves++;
                isHumanTurn=true;
            }
        }
        if (moves == 9){
            System.out.println("The game ends in a draw");
            display(arr);
        }
        else {
            if (isHumanTurn) {
                System.out.println("Computer has won!");
                display(arr);
            }//because if its the player's turn that means the game ended with computer's move making it the winner
            else {
                System.out.println("The Player has won!");
                display(arr);
            }

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to tic tac toe with AI");
        play(true);
    }
}
