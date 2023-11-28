import java.util.*;

class Graph{
    private int val;
    private LinkedList<Integer> adj[];

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    Graph(int size){
        val = size;
        adj = new LinkedList[val];

        for (int i = 0; i < val; i++) {
            adj[i] = new LinkedList<>();
        }

    }

    public void addEdge(int s, int e){
        adj[s].add(e);
        adj[e].add(s);
    }
}

public class BFS {

    private static ArrayList<Integer> bfs;
    public static void bfsTraversal(LinkedList<Integer> adj[], int size){
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[size];

        visited[0] = true;
        q.add(0);
        bfs = new ArrayList<>();

        while (q.size() != 0){
            int s = q.poll();
            bfs.add(s);
            for(Integer num:adj[s]){
                if(visited[num] == false){
                    visited[num] = true;
                    q.add(num);
                }
            }
        }

        System.out.println();
        for(Integer num:bfs){
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the size of the graph: ");
        int size = scan.nextInt();
        Graph g = new Graph(size);
        int edgeSize=0, s=0,e=0;
        System.out.println("Enter the number of edges of the graph");
        edgeSize = scan.nextInt();
        for (int i = 0; i < edgeSize; i++) {
            System.out.println("Enter the starting vertex and ending vertex of the edge");
            s=scan.nextInt();
            e=scan.nextInt();
            g.addEdge(s,e);
        }

        LinkedList<Integer> adj[] = g.getAdj();

        bfsTraversal(adj, size);
    }
}
