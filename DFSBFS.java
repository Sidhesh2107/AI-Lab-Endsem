import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DFSBFS {
    static class Node {
        int source;
        int destination;

        public Node(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    public static void createGraph(ArrayList<ArrayList<Node>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            graph.set(i, new ArrayList<Node>());
        }
        graph.get(0).add(new Node(0, 1));
        graph.get(0).add(new Node(0, 2));
        graph.get(0).add(new Node(0, 3));

        graph.get(1).add(new Node(1, 0));
        graph.get(1).add(new Node(1, 4));

        graph.get(2).add(new Node(2, 0));
        graph.get(2).add(new Node(2, 4));
        graph.get(2).add(new Node(2, 5));

        graph.get(3).add(new Node(3, 0));
        graph.get(3).add(new Node(3, 6));

        graph.get(4).add(new Node(4, 1));
        graph.get(4).add(new Node(4, 2));
        graph.get(4).add(new Node(4, 5));
        graph.get(4).add(new Node(4, 7));

        graph.get(5).add(new Node(5, 2));
        graph.get(5).add(new Node(5, 4));
        graph.get(5).add(new Node(5, 6));
        graph.get(5).add(new Node(5, 7));

        graph.get(6).add(new Node(6, 3));
        graph.get(6).add(new Node(6, 5));
        graph.get(6).add(new Node(6, 8));

        graph.get(7).add(new Node(7, 4));
        graph.get(7).add(new Node(7, 5));
        graph.get(7).add(new Node(7, 8));
        graph.get(7).add(new Node(7, 9));

        graph.get(8).add(new Node(8, 6));
        graph.get(8).add(new Node(8, 7));
        graph.get(8).add(new Node(8, 9));

        graph.get(9).add(new Node(9, 7));
        graph.get(9).add(new Node(9, 8));
        graph.get(9).add(new Node(9, 10));

        graph.get(10).add(new Node(10, 9));

    }

    public static void BFS(ArrayList<ArrayList<Node>> graph, int V, boolean[] vis, int start) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        vis[start] = true;

        while (!q.isEmpty()) {
            int value = q.poll();
            System.out.print(value + " ");

            for (Node e : graph.get(value)) {
                if (!vis[e.destination]) {
                    q.add(e.destination);
                    vis[e.destination] = true;
                }
            }
        }
    }

    public static void DFS(ArrayList<ArrayList<Node>> graph, int value, boolean[] vis) {
        System.out.print(value + " ");
        vis[value] = true;
        for (int i = 0; i < graph.get(value).size(); i++) {
            Node e = graph.get(value).get(i);
            if (vis[e.destination] == false) {
                DFS(graph, e.destination, vis);
            }
        }
    }

    public static void displayGraphStructure(ArrayList<ArrayList<Node>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("Node " + i + " neighbors-> ");
            for (Node node : graph.get(i)) {
                System.out.print(node.destination + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Node>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        boolean[] vis = new boolean[V];
        System.out.println("Graph Structure:");
        displayGraphStructure(graph);

        System.out.println("\nBFS Traversal");
        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                BFS(graph, V, vis, i);
            }
        }

        System.out.println("\nDFS Traversal");
        vis = new boolean[V];
        DFS(graph, 0, vis);

    }

}
