import java.util.*;

class PuzzleNode implements Comparable<PuzzleNode> {
    int[][] puzzle;
    int cost;
    int heuristic;
    int depth;
    PuzzleNode parent;

    public PuzzleNode(int[][] puzzle, int depth, PuzzleNode parent) {
        this.puzzle = puzzle;
        this.depth = depth;
        this.parent = parent;
        this.cost = calculateCost();
        this.heuristic = calculateHeuristic();
    }

    private int calculateCost() {
        int cost = 0;
        int goal[][] = {
            {1, 2, 3},
            {8, 0, 5},
            {7, 6, 4}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] != goal[i][j] && puzzle[i][j] != 0) {
                    cost++;
                }
            }
        }
        return cost;
    }

    private int calculateHeuristic() {
        int h = 0;
        int goal[][] = {
            {1, 2, 3},
            {8, 0, 5},
            {7, 6, 4}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = puzzle[i][j];
                if (value != 0) {
                    int goalX = (value - 1) / 3;
                    int goalY = (value - 1) % 3;
                    h += Math.abs(i - goalX) + Math.abs(j - goalY);
                }
            }
        }
        return h;
    }

    @Override
    public int compareTo(PuzzleNode other) {
        return (this.cost + this.heuristic) - (other.cost + other.heuristic);
    }
}

public class Astar8puzzle {

    public static void main(String[] args) {
        int initial[][] = {
            {2, 8, 3},
            {1, 6, 5},
            {7, 0, 4}
        };

        solvePuzzle(initial);
    }

    public static void solvePuzzle(int[][] initial) {
        PriorityQueue<PuzzleNode> queue = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        PuzzleNode root = new PuzzleNode(initial, 0, null);
        queue.add(root);

        while (!queue.isEmpty()) {
            PuzzleNode current = queue.poll();
            visited.add(Arrays.deepToString(current.puzzle));

            if (current.cost == 0) {
                printSolution(current);
                return;
            }

            int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int[] move : moves) {
                int newX = move[0] + getBlankPosition(current.puzzle)[0];
                int newY = move[1] + getBlankPosition(current.puzzle)[1];

                if (isValid(newX, newY)) {
                    int[][] newPuzzle = swap(current.puzzle, getBlankPosition(current.puzzle), new int[]{newX, newY});

                    if (!visited.contains(Arrays.deepToString(newPuzzle))) {
                        PuzzleNode newNode = new PuzzleNode(newPuzzle, current.depth + 1, current);
                        queue.add(newNode);
                    }
                }
            }
        }

        System.out.println("No solution found.");
    }

    private static int[] getBlankPosition(int[][] puzzle) {
        int[] position = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private static int[][] swap(int[][] puzzle, int[] pos1, int[] pos2) {
        int[][] newPuzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newPuzzle[i][j] = puzzle[i][j];
            }
        }
        int temp = newPuzzle[pos1[0]][pos1[1]];
        newPuzzle[pos1[0]][pos1[1]] = newPuzzle[pos2[0]][pos2[1]];
        newPuzzle[pos2[0]][pos2[1]] = temp;
        return newPuzzle;
    }

    private static void printSolution(PuzzleNode node) {
        Stack<PuzzleNode> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.parent;
        }
        int steps = 0;
        while (!stack.isEmpty()) {
            PuzzleNode current = stack.pop();
            System.out.println("Step " + steps++);
            printPuzzle(current.puzzle);
        }
    }

    private static void printPuzzle(int[][] puzzle) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

