import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] arr = new String[9];
        int temp;
        boolean flag=false;
        String con = "";
        int c=0;

        System.out.println();
        for (int i = 0; i < 9; i++) {
            arr[i] = String.valueOf(i);
            System.out.print(arr[i] + " ");
            if (i == 2 || i == 5){
                System.out.println();
            }
        }
        System.out.println();

        System.out.println("player 1 is X and computer is O");

        while (flag == false){

            while (true) {
                System.out.println("Enter the position where player 1 wants to put X");
                temp = scan.nextInt();

                if (arr[temp] == "X" || arr[temp] == "O") {
                    System.out.println("That position is full choose another position");
                } else if (temp<0 || temp>= arr.length) {
                    System.out.println("Invalid input, position does not exist");
                } else {
                    arr[temp] = "X";
                    break;
                }
            }
            c++;

            for (int i = 0; i < 9; i++) {
                if (arr[i] != "X" && arr[i] != "O"){
                    arr[i] = "O";
                    break;
                }
            }

            System.out.println();
            for (int i = 0; i < 9; i++) {
                System.out.print(arr[i] + " ");
                if (i == 2 || i == 5){
                    System.out.println();
                }
            }
            System.out.println();

            for (int i = 0; i < 8; i++) {
                con = null;
                switch (i){
                    case 0:
                        con = arr[0] + arr[1] + arr[2];
                        break;

                    case 1:
                        con = arr[3] + arr[4] + arr[5];
                        break;

                    case 2:
                        con = arr[6] + arr[7] + arr[8];
                        break;

                    case 3:
                        con = arr[0] + arr[3] + arr[6];
                        break;

                    case 4:
                        con = arr[1] + arr[4] + arr[7];
                        break;

                    case 5:
                        con = arr[2] + arr[5] + arr[8];
                        break;

                    case 6:
                        con = arr[0] + arr[4] + arr[8];
                        break;

                    case 7:
                        con = arr[2] + arr[4] + arr[6];
                        break;
                }

                if (con.equals("XXX")){
                    System.out.println("Player (X) is the winner");
                    flag = true;
                    break;
                } else if (con.equals("OOO")) {
                    System.out.println("Computer (O) is the winner");
                    flag = true;
                    break;
                }
            }

            if (c == 5 && flag == false){
                System.out.println("The match ends in a draw");
                flag = true;
            }
        }

    }
}