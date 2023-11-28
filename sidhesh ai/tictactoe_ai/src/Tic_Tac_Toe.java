import java.util.*;

public class Tic_Tac_Toe {

    static ArrayList<Integer> playPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] arg){

        char[][] gameBoard = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}
        };
        printGameBoard(gameBoard);
        while(true){
            Scanner sc = new Scanner (System.in);
            System.out.println("Enter your placement (1-9) player1: ");
            int playerPos = sc.nextInt();

            // checking if position is taken or not
            while(playPositions.contains(playerPos)|| cpuPositions.contains(playPositions)){
                System.out.println("position taken ! Enter a correct position ");
                playerPos = sc.nextInt();
            }
            // updates board
            placePiece(gameBoard,playerPos,"player");

            String result = checkWinner();
            if(!result.isEmpty()){
                System.out.println(result);
                printGameBoard(gameBoard);
                break;
            }

            System.out.println("Enter your placement (1-9) player2: ");
            int cpuPos = findBestMove(gameBoard);
            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                printGameBoard(gameBoard);
                break;
            }
        }



    }
    public static void printGameBoard(char[][] GameBoard){
        for(char[] row : GameBoard){
            for(char c : row){
                System.out.print(c);

            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] gameBoard, int pos,String user){
        char symbol = ' ';

        // adds X or O symbol to respective players array
        if(user.equals("player")){
            symbol = 'X';
            playPositions.add(pos);
        }else{
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] =symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] =symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String checkWinner() {
        List torRaw = Arrays.asList(1,2,3);
        List midRaw = Arrays.asList(4,5,6);
        List botRaw = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);
        List<List> winning = new ArrayList<>();

        // winning combinations are  added to an ArrayList called winning
        winning.add(torRaw);
        winning.add(botRaw);
        winning.add(midRaw);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);


        // checks if any player have winnig combination
        for(List l: winning){
            if(playPositions.containsAll(l)){
                return "Congrats player1 won!";
            }else if(cpuPositions.containsAll(l)){
                return "Congrats player2 won!";
            }else if(playPositions.size() + cpuPositions.size() == 9){
                return "Its a DRAW!!!!!";

            }
        }
        return "";
    }
    public static int findBestMove(char[][] gameBoard) {
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 1; i <= 9; i++) {
            if (!playPositions.contains(i) && !cpuPositions.contains(i)) {
                char[][] boardCopy = new char[5][5];
                for (int row = 0; row < 5; row++) {
                    boardCopy[row] = gameBoard[row].clone();
                }

                placePiece(boardCopy, i, "cpu");
                int score = minimax(boardCopy, 0, false);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    public static int minimax(char[][] board, int depth, boolean maximizingPlayer) {
        String result = checkWinner();
        if (result.equals("Congrats player1 won!")) {
            return -1;
        } else if (result.equals("Congrats player2 won!")) {
            return 1;
        } else if (result.equals("Its a DRAW!!!!!")) {
            return 0;
        }

        int bestScore;
        if (maximizingPlayer) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 1; i <= 9; i++) {
                if (!playPositions.contains(i) && !cpuPositions.contains(i)) {
                    char[][] boardCopy = new char[5][5];
                    for (int row = 0; row < 5; row++) {
                        boardCopy[row] = board[row].clone();
                    }

                    placePiece(boardCopy, i, "cpu");
                    int score = minimax(boardCopy, depth + 1, false);
                    bestScore = Math.max(bestScore, score);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 1; i <= 9; i++) {
                if (!playPositions.contains(i) && !cpuPositions.contains(i)) {
                    char[][] boardCopy = new char[5][5];
                    for (int row = 0; row < 5; row++) {
                        boardCopy[row] = board[row].clone();
                    }

                    placePiece(boardCopy, i, "player");
                    int score = minimax(boardCopy, depth + 1, true);
                    bestScore = Math.min(bestScore, score);
                }
            }
        }

        return bestScore;
    }
}