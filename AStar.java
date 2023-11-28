import java.util.*;

class Graph {
    private int V;
    private Map<Integer, List<Node>> adj;

    public Graph(int V) {
        this.V = V;
        this.adj = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Node(v, weight));
        adj.get(v).add(new Node(u, weight));
    }

    public List<Integer> shortestPath(int start, int goal, int[] heuristic) {

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
        Set<Integer> closedSet = new HashSet<>();

        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        for (int i = 0; i < V; i++) {
            gScore.put(i, Integer.MAX_VALUE);
        }

        gScore.put(start, 0);
        openSet.add(new Node(start, heuristic[start]));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            closedSet.add(current.id);

            if (current.id == goal) {
                return reconstructPath(parent, start, goal);
            }

            for (Node neighbor : adj.get(current.id)) {
                if (closedSet.contains(neighbor.id)) {
                    continue;
                }

                int tentativeGScore = gScore.get(current.id) + neighbor.weight;
                if (tentativeGScore < gScore.get(neighbor.id)) {
                    parent.put(neighbor.id, current.id);
                    gScore.put(neighbor.id, tentativeGScore);
                    int fScore = tentativeGScore + heuristic[neighbor.id];
                    openSet.add(new Node(neighbor.id, fScore));
                }
            }
        }

        return null;
    }

    private List<Integer> reconstructPath(Map<Integer, Integer> parent, int start, int goal) {
        List<Integer> path = new ArrayList<>();
        int current = goal;
        while (parent.containsKey(current)) {
            path.add(current);
            current = parent.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private static class Node {
        int id;
        int weight;
        int f;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
}

public class AStar {
    public static void main(String[] args) {

        Graph graph = new Graph(8);

        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 5);
        graph.addEdge(0, 3, 10);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 4, 6);
        graph.addEdge(2, 5, 7);
        graph.addEdge(3, 5, 5);
        graph.addEdge(4, 6, 4);
        graph.addEdge(5, 6, 6);
        graph.addEdge(6, 7, 3);

        int start = 0;
        int goal = 7;

        int[] heuristic = { 17, 10, 15, 4, 4, 5, 1, 0 };

        List<Integer> path = graph.shortestPath(start, goal, heuristic);

        if (path != null) {
            System.out.println("Shortest Path from " + start + " to " + goal + ": " + path);
        } else {
            System.out.println("No path found");
        }
    }
}
